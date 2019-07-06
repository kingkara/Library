package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Override
    Book save(Book book);

    Book getBookById(long id);

    Book getBookByTitleTitleContains(String title);

    int countBookByTitleTitleContains(String title);

    List<Book> findBooksByTitleTitleContains(String title);
}
