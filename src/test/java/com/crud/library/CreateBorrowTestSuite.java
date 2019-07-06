package com.crud.library;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.mapper.UserMapper;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowRepository;
import com.crud.library.repository.TitleRepository;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
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
public class CreateBorrowTestSuite {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DbService dbService;
    @Autowired
    private TitleRepository titleRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void createBorrow() {
        User user = new User();
        user.setFirstName("Mark");
        user.setLastName("Twain");
        user.setDateOfCreatingAccount(LocalDate.of(2019, 06, 05));

        dbService.addUser(userRepository.save(user));
        long userId = user.getId();

        Title title = new Title();
        title.setTitle("New great world");
        title.setAuthor("Jane Huxley");
        title.setYearOfPublish(LocalDate.of(1965, 12, 06));

        dbService.addTitle(titleRepository.save(title));
        long titleId = title.getTitleId();

        Book book = new Book();
        book.setTitle(dbService.getTitle("New great world"));
        book.setStatus("available");

        dbService.addBook(bookRepository.save(book));
        long bookId = book.getId();

        Borrow borrow = new Borrow();
        borrow.setBook(dbService.getBook("New great world"));
        borrow.setUser(dbService.getUser(userId));
        borrow.setDateOfBorrow(LocalDate.now());
        borrow.setDateOfReturn(LocalDate.now().plusDays(30));

        //When
        dbService.addBorrow(borrow);

        //Then
        long borrowId = borrow.getId();

        Optional<Borrow> borrowGet = borrowRepository.findById(borrowId);
        Assert.assertEquals(borrowId, borrowGet.get().getId());
        Assert.assertEquals("New great world", borrowGet.get().getBook().getTitle().getTitle());
        Assert.assertEquals(userId, borrowGet.get().getUser().getId());
        Assert.assertEquals(LocalDate.now(), borrow.getDateOfBorrow());
        Assert.assertEquals(LocalDate.now().plusDays(30), borrow.getDateOfReturn());

        //CleanUp
        borrowRepository.deleteById(borrowId);
        bookRepository.deleteById(bookId);
        titleRepository.deleteById(titleId);
        userRepository.deleteById(userId);

    }

    @Test
    public void createBorrowDto() {
        //Given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mark");
        userDto.setLastName("Twain");
        userDto.setDateOfCreatingAccount(LocalDate.of(2019, 06, 05));

        TitleDto titleDto = new TitleDto();
        titleDto.setTitle("New great world");
        titleDto.setAuthor("Jane Huxley");
        titleDto.setYearOfPublish(LocalDate.of(1965, 12, 06));

        BookDto bookDto = new BookDto(
                (long) 1,
                titleMapper.mapToTitle(titleDto),
                "borrowed");

        BorrowDto borrowDto = new BorrowDto(
                (long) 1,
                bookMapper.mapToBook(bookDto),
                userMapper.mapToUser(userDto),
                LocalDate.of(2019, 05, 10),
                LocalDate.of(2019, 05, 30));

        //When
        long id = borrowDto.getId();
        long bookId = borrowDto.getBook().getId();
        long userId = borrowDto.getUser().getId();
        LocalDate dateOfBorrow = borrowDto.getDateOfBorrow();
        LocalDate dateOfReturn = borrowDto.getDateOfReturn();

        //Then
        Assert.assertEquals(1, id);
        Assert.assertEquals(1, bookId);
        Assert.assertEquals(1, userId);
        Assert.assertEquals(LocalDate.of(2019, 05, 10), dateOfBorrow);
        Assert.assertEquals(LocalDate.of(2019, 05, 30), dateOfReturn);
    }
}
