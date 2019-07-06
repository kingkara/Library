package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
public Book mapToBook (final BookDto bookDto) {
    return new Book(
            bookDto.getId(),
            bookDto.getTitle(),
            bookDto.getStatus());
}

public BookDto maptoBookDto (final Book book) {
    return new BookDto(
            book.getId(),
            book.getTitle(),
            book.getStatus());
}
}
