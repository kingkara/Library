package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.Borrow;
import com.crud.library.domain.User;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("v1/library")
public class BorrowController {
    @Autowired
    private DbService service;

    @RequestMapping(method = RequestMethod.PUT, value = "borrowBook")
    public void borrowBook(@RequestParam String title, long userId) {
        List<Book> books = (service.getAvailableBooks(title));
        User user = service.getUser(userId);

        for(Book book: books) {
            if (book.getStatus().equals("available")) {
                book.borrowBook();
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
            book.returnBook();
            service.deleteBorrow(borrowId);
        }

    }
}
