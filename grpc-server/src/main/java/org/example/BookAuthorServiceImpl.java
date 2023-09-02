package org.example;

import com.example.proto.Author;
import com.example.proto.Book;
import com.example.proto.BookServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.repository.Books;

@GrpcService
public class BookAuthorServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    @Override
    public void getAuthor(Book request, StreamObserver<Author> responseObserver) {
        Books.getAuthors()
                .stream()
                .peek( a-> a.getBookId())
                .filter( book -> book.getAuthorId() == request.getAuthorId())
                .findFirst()
                .ifPresent(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
