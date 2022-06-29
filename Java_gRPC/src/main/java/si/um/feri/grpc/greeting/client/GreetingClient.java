package si.um.feri.grpc.greeting.client;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
import si.um.feri.greet.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("Hello, I'm a gRPC client!");
        GreetingClient main = new GreetingClient();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number (1 - 5): ");
        var number = scanner.nextInt();
        main.run(number);
    }

    private void run(int number) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();//NOT FOR PRODUCTION

        ManagedChannel secureChannel = null;
        try {
            secureChannel = Grpc.newChannelBuilder("localhost:50051", TlsChannelCredentials.newBuilder().trustManager(new File("ssl/ca.crt")).build()).build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (number) {
            case 1 -> doUnaryCall(channel);
            case 2 -> doServerStreamingCall(channel);
            case 3 -> doClientStreamingCall(channel);
            case 4 -> doBiDiStreamingCall(channel);
            case 5 -> doUnaryCallWithDeadline(channel);
            default -> System.out.println("Wrong number!");
        }

        System.out.println("Shutting down channel...");
        channel.shutdown();
    }

    private void doUnaryCall(ManagedChannel channel) {
        System.out.println("doUnaryCall");
        //created a greet service client (blocking -> synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        //Unary
        //created a protocol buffer greeting message
        Greeting greeting = Greeting.newBuilder().setFirstName("Bine").setLastName("Yulovski").build();
        //do the same for a GreetRequest
        GreetRequest greetRequest = GreetRequest.newBuilder().setGreeting(greeting).build();
        //call the RPC and get back a GreetResponse (protocol buffers)
        GreetResponse greetResponse = greetClient.greet(greetRequest);
        System.out.println(greetResponse.getResult());
    }

    private void doServerStreamingCall(ManagedChannel channel) {
        System.out.println("doServerStreamingCall");
        //created a greet service client (blocking -> synchronous)
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);
        GreetManyTimesRequest greetManyTimesRequest = GreetManyTimesRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Yule")).build();
        greetClient.greetManyTimes(greetManyTimesRequest).forEachRemaining(greetManyTimesResponse -> System.out.println(greetManyTimesResponse.getResult()));
    }

    private void doClientStreamingCall(ManagedChannel channel) {
        System.out.println("doClientStreamingCall");
        //create an async client
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<LongGreetRequest> requestObserver = asyncClient.longGreet(new StreamObserver<>() {
            @Override
            public void onNext(LongGreetResponse value) {
                //we get a response from the server
                System.out.println("Received a response from the server");
                System.out.println(value.getResult());
                //onNext will be called only once
            }

            @Override
            public void onError(Throwable t) {
                //we get an error from the server
            }

            @Override
            public void onCompleted() {
                //the server is done sending us data
                System.out.println("Server has completed sending us something");
                //onCompleted will be called right after onNext()
                latch.countDown();
            }
        });
        //streaming message #1
        System.out.println("Sending message #1");
        requestObserver.onNext(LongGreetRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Bine").build()).build());
        //streaming message #2
        System.out.println("Sending message #2");
        requestObserver.onNext(LongGreetRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Yule").build()).build());
        //streaming message #3
        System.out.println("Sending message #3");
        requestObserver.onNext(LongGreetRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Tai").build()).build());
        //we tell the server that the client is done sending data
        requestObserver.onCompleted();

        try {
            latch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doBiDiStreamingCall(ManagedChannel channel) {
        System.out.println("doBiDiStreamingCall");
        //create an async client
        GreetServiceGrpc.GreetServiceStub asyncClient = GreetServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<GreetEveryoneRequest> requestObserver = asyncClient.greetEveryone(new StreamObserver<GreetEveryoneResponse>() {
            @Override
            public void onNext(GreetEveryoneResponse value) {
                System.out.println("Response from server: " + value.getResult());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server is done sending data");
                latch.countDown();
            }
        });

        Arrays.asList("Bine", "Yule", "Tai", "Bile").forEach(name -> {
            System.out.println("Sending: " + name);
            requestObserver.onNext(GreetEveryoneRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName(name)).build());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        requestObserver.onCompleted();

        try {
            latch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doUnaryCallWithDeadline(ManagedChannel channel) {
        GreetServiceGrpc.GreetServiceBlockingStub blockingStub = GreetServiceGrpc.newBlockingStub(channel);

        //first call (3000 ms deadline)
        try {
            System.out.println("Sending a request with deadline of a 3000 ms");
            GreetWithDeadlineResponse response = blockingStub.withDeadline(Deadline.after(3000, TimeUnit.MILLISECONDS)).greetWithDeadline(GreetWithDeadlineRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Bine").getDefaultInstanceForType()).build());
            System.out.println(response.getResult());
        } catch (StatusRuntimeException e) {
            if (e.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("Deadline has been exceeded, we don't want the response");
            } else {
                e.printStackTrace();
            }
        }

        //first call (100 ms deadline)
        try {
            System.out.println("Sending a request with deadline of a 100 ms");
            GreetWithDeadlineResponse response = blockingStub.withDeadline(Deadline.after(100, TimeUnit.MILLISECONDS)).greetWithDeadline(GreetWithDeadlineRequest.newBuilder().setGreeting(Greeting.newBuilder().setFirstName("Bine").getDefaultInstanceForType()).build());
            System.out.println(response.getResult());
        } catch (StatusRuntimeException e) {
            if (e.getStatus() == Status.DEADLINE_EXCEEDED) {
                System.out.println("Deadline has been exceeded, we don't want the response");
            } else {
                e.printStackTrace();
            }
        }
    }
}
