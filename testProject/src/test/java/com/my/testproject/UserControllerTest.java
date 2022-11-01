package com.my.testproject;

import com.my.testproject.model.businessLayer.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-integrationtest.properties")
//@Sql(value = {"create-users.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"after-create-users.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private static Stream<Arguments> findUserByIdProvider() {
        long id = 0;
        return Stream.of(
                Arguments.of(++id, new User("Alejandrina", "Doherty", LocalDate.of(1992, 6, 10))),
                Arguments.of(++id, new User("Curtis", "Warden", LocalDate.of(1987, 1, 29))),
                Arguments.of(++id, new User("Zaccaria", "Silkston", LocalDate.of(1998, 6, 9)))
        );
    }

    @ParameterizedTest
    @MethodSource("findUserByIdProvider")
    void findUserById(long id, User user) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/" + id))
                //.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(user.getLastName())))
                .andExpect(jsonPath("$.age", equalTo(user.getAge())));
    }
}
