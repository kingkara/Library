package com.crud.library;

import com.crud.library.domain.User;
import com.crud.library.domain.UserDto;
import com.crud.library.repository.UserRepository;
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
public class CreateUserTestSuite {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DbService dbService;
    @Test
    public void createUser() {
        //Given
        User user = new User();
        user.setFirstName("Mark");
        user.setLastName("Twain");
        user.setDateOfCreatingAccount(LocalDate.of(2019, 06, 05));

        //When
        dbService.addUser(userRepository.save(user));

        //Then
        long id = user.getId();
        Optional<User> user1 = userRepository.findById(id);
        Assert.assertEquals((long)1, user1.get().getId());
        Assert.assertEquals("Mark", user1.get().getFirstName());
        Assert.assertEquals("Twain",user1.get().getLastName());
        Assert.assertEquals(LocalDate.of(2019,06,05), user1.get().getDateOfCreatingAccount());

        //CleanUp
        userRepository.deleteById(id);
    }
    @Test
    public void createUserDto() {
        //Given
        UserDto userDto = new UserDto();
        userDto.setFirstName("Mark");
        userDto.setLastName("Twain");
        userDto.setDateOfCreatingAccount(LocalDate.of(2019, 06, 05));

        //When
        long id = userDto.getId();
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        LocalDate date = userDto.getDateOfCreatingAccount();

        //Then
        Assert.assertEquals(1, id);
        Assert.assertEquals("Mark", firstName);
        Assert.assertEquals("Smith", lastName);
        Assert.assertEquals(LocalDate.of(2019,06,20), date);
    }
}
