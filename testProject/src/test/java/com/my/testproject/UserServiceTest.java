package com.my.testproject;

import com.my.testproject.model.businessLayer.User;
import com.my.testproject.model.businessLayer.UserService;
import com.my.testproject.model.businessLayer.UserServiceImpl;
import com.my.testproject.model.businessLayer.exception.UserNotFoundException;
import com.my.testproject.model.persistenceLayer.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.assertj.ApplicationContextAssert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @MockBean
        private UserRepository userRepository;

        @Bean
        UserServiceImpl testUserService() {
//            User user = new User("testName", "testLastName", LocalDate.of(2000, 10, 10));
//            when(userRepository.findById(any()))
//                    .thenReturn(Optional.of(user));
            when(userRepository.findById(any()))
                    .thenReturn(Optional.empty());
            return new UserServiceImpl(userRepository);
        }
    }


    @Qualifier("testUserService")
    @Autowired
    private UserService userService;

    @Test
    void testGetEmptyUserById() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(2L));
    }
}
