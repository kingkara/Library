package com.crud.library;

import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import com.crud.library.repository.TitleRepository;
import com.crud.library.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreateTitleTestSuite {

    @Autowired
    private DbService dbService;
    @Autowired
    private TitleRepository titleRepository;

    @Test
    public void createTitle() {
        //Given
        Title title = new Title();
        title.setTitle("New great world");
        title.setAuthor("Jane Huxley");
        title.setYearOfPublish(LocalDate.of(1965,12,06));

        //When
        dbService.addTitle(titleRepository.save(title));

        //Then
        long id = title.getTitleId();
        Optional<Title> titleGet = titleRepository.findById(id);
        Assert.assertEquals(id, titleGet.get().getTitleId());
        Assert.assertEquals("New great world", titleGet.get().getTitle());
        Assert.assertEquals("Jane Huxley", titleGet.get().getAuthor());
        Assert.assertEquals(LocalDate.of(1965,12,06), titleGet.get().getYearOfPublish());

        //CleanUp
        titleRepository.deleteById(id);
    }

    @Test
    public void createTitleDto() {
        //Given
        TitleDto titleDto = new TitleDto();
        titleDto.setTitle("New great world");
        titleDto.setAuthor("Jane Huxley");
        titleDto.setYearOfPublish(LocalDate.of(1965,12,06));

        //When
        long id = titleDto.getTitleId();
        String title = titleDto.getTitle();
        String author = titleDto.getAuthor();
        LocalDate date = titleDto.getYearOfPublish();

        //Then
        Assert.assertEquals(1, id);
        Assert.assertEquals("New great world", title);
        Assert.assertEquals("Jane Huxley", author);
        Assert.assertEquals(LocalDate.of(1965, 12, 06), date);
    }
}
