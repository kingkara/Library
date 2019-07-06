package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRepository;
import com.crud.library.repository.TitleRepository;
import com.crud.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;


    public User addUser(final User user) {

        return userRepository.save(user);
    }

    public User getUser(final long id) {
        return userRepository.getById(id);
    }

    public Title addTitle(final Title title)  {
            return titleRepository.save(title);
    }

    public Title getTitle(final String title) {
        return titleRepository.getByTitleEquals(title);
    }

    public int getTitleCount(final String title) {
        return bookRepository.countBookByTitleTitleContains(title);
    }

    public Book addBook(final Book book) {

        return bookRepository.save(book);
    }

    public Book getBook(final String title) {
        return bookRepository.getBookByTitleTitleContains(title);
    }

    public Book getBookById(final long id) {
        return bookRepository.getBookById(id);
    }

    public List<Book> getAvailableBooks (final String title) {
        return bookRepository.findBooksByTitleTitleContains(title);
    }

    public Borrow addBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public Borrow getBorrow(final long id) {
        return borrowRepository.findById(id).orElse(null);
    }

    public void deleteBorrow(final long id) {
        borrowRepository.deleteById(id);
    }

}
