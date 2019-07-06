package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v1/library")
public class LibraryController {
    @Autowired
    private DbService service;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.POST, value = "addUser", consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody UserDto userDto) {
        service.addUser(userMapper.mapToUser(userDto));

    }

    @RequestMapping(method = RequestMethod.POST, value = "addTitle", consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        service.addTitle(titleMapper.mapToTitle(titleDto));

    }

    @RequestMapping(method = RequestMethod.GET, value = "getTitle")
    public TitleDto getTitle(@RequestParam String title){
        return titleMapper.mapToTitleDto(service.getTitle(title));
    }

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

    @RequestMapping(method = RequestMethod.PUT, value = "borrowBook")
    public void borrowBook(@RequestParam String title, long userId) {
        List<Book> books = (service.getAvailableBooks(title));
        User user = service.getUser(userId);

        for(Book book: books) {
            if (book.getStatus().equals("available")) {
                book.setStatus("borrowed");
               BookDto bookDto = bookMapper.maptoBookDto(book);
               changeStatus(bookDto);
               Borrow borrow = new Borrow();
               borrow.setUser(user);
               borrow.setBook(book);
               borrow.setDateOfBorrow(LocalDate.now());
               borrow.setDateOfReturn(LocalDate.now().plusDays(30));
               service.addBorrow(borrow);
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook")
    public void returnBook (@RequestParam long borrowId) {
        Borrow borrow = service.getBorrow(borrowId);
        if(borrow!=null) {
            Book book = borrow.getBook();
            book.setStatus("available");
            BookDto bookDto = bookMapper.maptoBookDto(book);
            changeStatus(bookDto);
            service.deleteBorrow(borrowId);
        }

    }
}
