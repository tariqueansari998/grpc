package org.example.controller;

import com.google.protobuf.Descriptors;
import org.example.service.BookAuthorClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class BookAuthorController {

    @Autowired
    BookAuthorClientService authorClientService;

    @GetMapping(value = "/v1/author/{authorId}")
    public Map<Descriptors.FieldDescriptor, Object> getAuthor(@PathVariable int authorId){
        return  authorClientService.getAuthor(authorId);
    }

}
