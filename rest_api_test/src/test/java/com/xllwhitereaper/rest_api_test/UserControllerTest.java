package com.xllwhitereaper.rest_api_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xllwhitereaper.rest_api_test.controllers.UserController;
import com.xllwhitereaper.rest_api_test.models.User;
import com.xllwhitereaper.rest_api_test.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private final User[] USERS = {
            new User(1L, "User1", "LastName1", "111111111", "email1@gmail.com", "definitelyNotMyPass1"),
            new User(2L, "User2", "LastName2", "222222222", "email2@gmail.com", "definitelyNotMyPass2"),
            new User(3L, "User3", "LastName3", "333333333", "email3@gmail.com", "definitelyNotMyPass3") };
    // User USER_1 = new User(1L, "User1", "LastName1", "111111111",
    // "email1@gmail.com", "definitelyNotMyPass1");
    // User USER_2 = new User(2L, "User2", "LastName2", "222222222",
    // "email2@gmail.com", "definitelyNotMyPass2");
    // User USER_3 = new User(3L, "User3", "LastName3", "333333333",
    // "email3@gmail.com", "definitelyNotMyPass3");

    // @Before()
    // public void setUp() {
    // mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    // }

    @Test
    public void getAllRecords_success() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        List<User> usersList = new ArrayList<>(Arrays.asList(USERS));

        Mockito.when(userRepository.findAll()).thenReturn(usersList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect((MockMvcResultMatchers.jsonPath("$[2].name", is(USERS[2].getName()))));
    }
}
