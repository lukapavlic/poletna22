package si.um.feri.grpc.blog.server;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.Document;
import org.bson.types.ObjectId;
import si.um.feri.blog.*;

import static com.mongodb.client.model.Filters.eq;

public class BlogServiceImpl extends BlogServiceGrpc.BlogServiceImplBase {

    private final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase database = mongoClient.getDatabase("grpcdb");
    private final MongoCollection<Document> collection = database.getCollection("blog");

    @Override
    public void createBlog(CreateBlogRequest request, StreamObserver<CreateBlogResponse> responseObserver) {
        System.out.println("Received Create Blog request");

        Blog blog = request.getBlog();

        Document doc = new Document("author_id", blog.getAuthorId()).append("title", blog.getTitle()).append("content", blog.getContent());

        System.out.println("Inserting Blog...");
        collection.insertOne(doc);

        String id = doc.getObjectId("_id").toString();
        System.out.println("Inserted Blog: " + id);
        CreateBlogResponse response = CreateBlogResponse.newBuilder().setBlog(blog.toBuilder().setId(id)).build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
        ;
    }

    @Override
    public void readBlog(ReadBlogRequest request, StreamObserver<ReadBlogResponse> responseObserver) {
        System.out.println("Received Read Blog request");

        String blogId = request.getBlogId();

        System.out.println("Searching for a Blog");

        Document result = null;
        try {
            result = collection.find(eq("_id", new ObjectId(blogId))).first();
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").augmentDescription(e.getLocalizedMessage()).asRuntimeException());
        }
        if (result == null) {
            System.out.println("Blog not found");
            responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").asRuntimeException());
        } else {
            System.out.println("Blog found");
            Blog blog = documentToBlog(result);

            responseObserver.onNext(ReadBlogResponse.newBuilder().setBlog(blog).build());

            responseObserver.onCompleted();
        }
    }

    @Override
    public void updateBlog(UpdateBlogRequest request, StreamObserver<UpdateBlogResponse> responseObserver) {
        System.out.println("Received Update Blog request");

        String blogId = request.getBlog().getId();

        System.out.println("Searching for a Blog so we can update it");

        Document result = null;
        try {
            result = collection.find(eq("_id", new ObjectId(blogId))).first();
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").augmentDescription(e.getLocalizedMessage()).asRuntimeException());
        }

        if (result == null) {
            System.out.println("Blog not found");
            responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").asRuntimeException());
        } else {
            Document replacement = new Document("author_id", request.getBlog().getAuthorId()).append("title", request.getBlog().getTitle()).append("content", request.getBlog().getContent()).append("_id", new ObjectId(blogId));

            System.out.println("Replacing Blog in database");
            collection.replaceOne(eq("_id", result.getObjectId("_id")), replacement);

            System.out.println("Replaced! Sending as a response");
            responseObserver.onNext(UpdateBlogResponse.newBuilder().setBlog(documentToBlog(replacement)).build());

            responseObserver.onCompleted();
        }
    }

    @Override
    public void deleteBlog(DeleteBlogRequest request, StreamObserver<DeleteBlogResponse> responseObserver) {
        System.out.println("Received Delete Blog Response");
        String blogId = request.getBlogId();

        DeleteResult result = null;
        try {
            result = collection.deleteOne(eq("_id", new ObjectId(blogId)));
        } catch (Exception e) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").augmentDescription(e.getLocalizedMessage()).asRuntimeException());
        }

        if (result != null) {
            if (result.getDeletedCount() == 0) {
                System.out.println("Blog not found");
                responseObserver.onError(Status.NOT_FOUND.withDescription("The blog with corresponding id was not found").asRuntimeException());
            } else {
                System.out.println("Blog was deleted");
                responseObserver.onNext(DeleteBlogResponse.newBuilder().setBlogId(blogId).build());
                responseObserver.onCompleted();
            }
        }

    }

    @Override
    public void listBlog(ListBlogRequest request, StreamObserver<ListBlogResponse> responseObserver) {
        System.out.println("Received List Blog Request");

        collection.find().forEach(document -> responseObserver.onNext(
                ListBlogResponse.newBuilder().setBlog(documentToBlog(document)).build()
        ));

        responseObserver.onCompleted();
    }

    private Blog documentToBlog(Document document) {
        return Blog.newBuilder()
                .setAuthorId(document.getString("author_id"))
                .setTitle(document.getString("title"))
                .setContent(document.getString("content"))
                .setId(document.getObjectId("_id").toString())
                .build();
    }
}
