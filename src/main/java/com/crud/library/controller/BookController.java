package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/library")
public class BookController {
    @Autowired
    private DbService service;
    @Autowired
    private BookMapper bookMapper;


    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        service.addBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "changeStatus")
    public BookDto changeStatus(@RequestBody BookDto bookDto) {
        return bookMapper.maptoBookDto(service.addBook(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public int getNumberOfBooks(@RequestParam String title) {
        return service.getTitleCount(title);
    }


}
