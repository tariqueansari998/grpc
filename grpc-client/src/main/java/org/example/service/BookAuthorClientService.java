package org.example.service;

import com.example.proto.Author;
import com.example.proto.Book;
import com.example.proto.BookServiceGrpc;
import com.example.proto.GreetingService;
import com.google.protobuf.Descriptors;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class BookAuthorClientService  {

    @GrpcClient("my-client")
    BookServiceGrpc.BookServiceBlockingStub synchronousClient;

    @GrpcClient("my-client")
    BookServiceGrpc.BookServiceStub asynchronousClient;



    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId){
        Book book = Book.newBuilder().setAuthorId(authorId).build();
        Author author = synchronousClient.getAuthor(book);
        return author.getAllFields();
    }

    public List<Map<Descriptors.FieldDescriptor, Object>> getBooksByAuthor(int authorId) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        List<Map<Descriptors.FieldDescriptor, Object>> response = new ArrayList<>();
        Author author = Author.newBuilder().setAuthorId(authorId).build();
        asynchronousClient.getBooksByAuthor(author, new StreamObserver<Book>() {
            @Override
            public void onNext(Book book) {
                response.add(book.getAllFields());
            }

            @Override
            public void onError(Throwable t) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });
        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptyList();
    }

}
