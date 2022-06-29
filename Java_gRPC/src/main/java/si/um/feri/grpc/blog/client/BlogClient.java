package si.um.feri.grpc.blog.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import si.um.feri.blog.*;

public class BlogClient {
    public static void main(String[] args) {
        System.out.println("gRPC Blog client");
        BlogClient main = new BlogClient();
        main.run();
    }

    private void run() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        BlogServiceGrpc.BlogServiceBlockingStub blogClient = BlogServiceGrpc.newBlockingStub(channel);

        Blog blog = Blog.newBuilder().setAuthorId("Bine").setTitle("New Blog").setContent("Hello Yule this is my first blog").build();

        CreateBlogResponse createResponse = blogClient.createBlog(CreateBlogRequest.newBuilder().setBlog(blog).build());

        System.out.println("Received create blog response");
        System.out.println(createResponse.toString());

        System.out.println("Reading blog");
        ReadBlogResponse readResponse = blogClient.readBlog(ReadBlogRequest.newBuilder().setBlogId(createResponse.getBlog().getId()).build());

        System.out.println(readResponse.toString());

        //System.out.println("Reading blog NOT FOUND");
        //ReadBlogResponse readResponseNotFound = blogClient.readBlog(ReadBlogRequest.newBuilder().setBlogId("6206808c3d9bd46ca0adb26g").build());

        //System.out.println(readResponseNotFound.toString());

        Blog newBlog = Blog.newBuilder().setId(createResponse.getBlog().getId()).setAuthorId("Yule").setTitle("New Blog (Updated)").setContent("Hello Bine this is my first blog").build();
        System.out.println("Updating blog...");
        UpdateBlogResponse updateBlogResponse = blogClient.updateBlog(UpdateBlogRequest.newBuilder().setBlog(newBlog).build());

        System.out.println("Updated blog");
        System.out.println(updateBlogResponse.toString());

        System.out.println("Deleting blog");
        DeleteBlogResponse deleteBlogResponse = blogClient.deleteBlog(DeleteBlogRequest.newBuilder().setBlogId(createResponse.getBlog().getId()).build());

        System.out.println("Reading blog");
        //this one should return NOT_FOUND
        //ReadBlogResponse readResponseAfterDeletion = blogClient.readBlog(ReadBlogRequest.newBuilder().setBlogId(createResponse.getBlog().getId()).build());

        //list the blogs in our database
        System.out.println("Reading blog -> LIST ALL");
        blogClient.listBlog(ListBlogRequest.newBuilder().build()).forEachRemaining(
                listBlogResponse -> {
                    System.out.println(listBlogResponse.getBlog());
                }
        );
    }
}
