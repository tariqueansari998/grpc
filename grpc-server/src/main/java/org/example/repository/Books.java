package org.example.repository;

import com.example.proto.Author;
import com.example.proto.Book;

import java.util.ArrayList;
import java.util.List;

public class Books {

    public static List<Author> getAuthors(){
        return new ArrayList<Author>(){
            {
                add(Author.newBuilder().setAuthorId(1).setName("Imam Bukhari").setBookId(1).build());
                add(Author.newBuilder().setAuthorId(2).setName("Imam Muslim").setBookId(2).build());
                add(Author.newBuilder().setAuthorId(3).setName("Imam Ahmed").setBookId(3).build());
            }
        }
        ;
    }

    public static List<Book> getBooks(){
        return new ArrayList<Book>(){
            {
                add(Book.newBuilder().setBookId(1).setPrice(100.0F).setTitle("sahih bukhari").setAuthorId(1).build());
                add(Book.newBuilder().setBookId(2).setPrice(100.0F).setTitle("sahih muslim").setAuthorId(2).build());
                add(Book.newBuilder().setBookId(3).setPrice(100.0F).setTitle("musnad ahmed").setAuthorId(3).build());
                add(Book.newBuilder().setBookId(4).setPrice(100.0F).setTitle("hasan bukhari").setAuthorId(1).build());
                add(Book.newBuilder().setBookId(5).setPrice(100.0F).setTitle("sahih ahmed").setAuthorId(3).build());
            }
        };
    }
}
