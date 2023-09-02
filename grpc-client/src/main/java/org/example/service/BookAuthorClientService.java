package org.example.service;

import com.example.proto.Author;
import com.example.proto.Book;
import com.example.proto.BookServiceGrpc;
import com.example.proto.GreetingService;
import com.google.protobuf.Descriptors;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class BookAuthorClientService  {

    @GrpcClient("my-client")
    BookServiceGrpc.BookServiceBlockingStub synchronousClient;



    public Map<Descriptors.FieldDescriptor, Object> getAuthor(int authorId){
        Book book = Book.newBuilder().setAuthorId(authorId).build();
        Author author = synchronousClient.getAuthor(book);
        return author.getAllFields();
    }

}
