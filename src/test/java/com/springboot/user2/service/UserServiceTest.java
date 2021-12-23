package com.springboot.user2.service;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.springboot.user2.payload.UserDto;
import com.springboot.user2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.user2.entity.User;
import com.springboot.user2.repository.UserRepository;
import com.springboot.user2.service.UserService;
import com.springboot.user2.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Test
    public void searchUserTest()
    {

        when(repository.findByFirstNameOrLastNameOrPin("sharad", "atule", "27642")).thenReturn(Stream.of(new User("1","sharad","atule","27642",new Date(17/11/2018),new Date(17/11/2018),"Yes"),new User("2","sharad","atule","27642",new Date(17/11/2018),new Date(17/11/2018),"Yes")).collect(Collectors.toList()));

        assertEquals(2,service.searchUser("sharad","atule","209312").size());
    }


    @Test
    public void findUserByIdTest()
    {
        Optional<User> user=Optional.of(new User("1","sharad","atule","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes"));
        when(repository.findById("1")).thenReturn(user);
        assertEquals(user, service.findUserById("1"));

    }

    @Test
    public void createUserTest()
    {
        User user=new User("1","sharad","atule","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes");
        when(repository.save(user)).thenReturn(user);
        assertEquals(user, service.createUser(new UserDto()));

    }



    @Test
    public void getUserSortedDOJTest()
    {

        when(repository.findByOrderByDateOfJoiningAsc()).thenReturn(Stream.of(new User("1","vikas","Singh","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes"),new User("2","vikas","Singh","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes")).collect(Collectors.toList()));
        assertEquals(2, service.getUserSortedDOJ().size());

    }

    @Test
    public void getUserSortedDOBTest()
    {

        when(repository.findByOrderByDateOfBirthAsc()).thenReturn(Stream.of(new User("1","vikas","Singh","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes"),new User("2","vikas","Singh","209312",new Date(17/11/2018),new Date(17/11/2018),"Yes")).collect(Collectors.toList()));
        assertEquals(2, service.getUserSortedDOB().size());

    }

    @Test
    public void hardDeleteUserTest()
    {

        //User user=new User("1","vikas","Singh","17/11/2018","1/11/2018","209312","Yes");
        service.deleteUserById("1");
        verify(repository,times(1)).deleteById("1");

    }

}