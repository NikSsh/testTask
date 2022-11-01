package com.my.testproject;

import com.my.testproject.model.businessLayer.User;
import com.my.testproject.model.persistenceLayer.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
@TestPropertySource("/application-integrationtest.properties")
class TestProjectApplicationTests {
    @Autowired
    UserRepository userRepo;

    @AfterEach
    void afterEach() {
        userRepo.deleteAll();
    }

    private static Stream<Arguments> testValidUserCreationProvider() {
        return Stream.of(
                Arguments.of(new User("Stredwick", "someSurname", LocalDate.of(2000, 1, 10))),
                Arguments.of(new User("Dudill", "someSurname", LocalDate.of(1995, 5, 12))),
                Arguments.of(new User("someName", "someSurname", LocalDate.of(1987, 7, 11)))
        );
    }

    @ParameterizedTest
    @MethodSource("testValidUserCreationProvider")
    void testValidUserCreation(User user) {
        Long savedUserId = userRepo.save(user).getId();
        assertNotNull(userRepo.findById(savedUserId).get());
    }
}
