package com.crud.library;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.TitleRepository;
import com.crud.library.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateBookTestSuite {
    @Autowired
    private DbService dbService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void createBook() {
        //Given
        Title title = new Title();
        title.setTitle("New great world");
        title.setAuthor("Jane Huxley");
        title.setYearOfPublish(LocalDate.of(1965,12,06));

        dbService.addTitle(titleRepository.save(title));

        Book book = new Book();
        book.setTitle(dbService.getTitle("New great world"));
        book.setStatus("available");

        long titleId = title.getTitleId();
        //When

        dbService.addBook(bookRepository.save(book));

        //Then
        long bookId = book.getId();
        Book bookGet = bookRepository.getBookById(bookId);
        Assert.assertEquals(bookId,bookGet.getId());
        Assert.assertEquals(titleId, bookGet.getTitle().getTitleId());
        Assert.assertEquals("available", bookGet.getStatus());

        //CleanUp
        bookRepository.deleteById(bookId);
        titleRepository.deleteById(titleId);

    }
    @Test
    public void createBookDto() {
        //Given
        TitleDto titleDto = new TitleDto();
        titleDto.setTitle("New great world");
        titleDto.setAuthor("Jane Huxley");
        titleDto.setYearOfPublish(LocalDate.of(1965,12,06));

        BookDto bookDto = new BookDto(
                (long) 1,
                titleMapper.mapToTitle(titleDto),
                "borrowed");
        //When

        long id = bookDto.getId();
        long titleId = titleDto.getTitleId();
        String status = bookDto.getStatus();

        //Then
        Assert.assertEquals(1, id);
        Assert.assertEquals(1, titleId);
        Assert.assertEquals("borrowed", status);
    }
}
