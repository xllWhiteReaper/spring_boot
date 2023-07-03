package com.xllwhitereaper.rest_api_test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;

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

        @Test
        public void shouldGetAllRecordsSuccessfully() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

                List<User> usersList = new ArrayList<>(Arrays.asList(USERS));

                Mockito.when(userRepository.findAll()).thenReturn(usersList);

                mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                                .andExpect((MockMvcResultMatchers.jsonPath("$[2].name", is(USERS[2].getName()))));
        }

        @Test
        public void shouldGetUserByIdIfFound() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

                long bookId = 1;
                Optional<User> foundUser = Optional.of(USERS[0]);

                Mockito.when(userRepository.findById(bookId)).thenReturn(foundUser);

                mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + bookId)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect((MockMvcResultMatchers.jsonPath("$.name", is(foundUser.get().getName()))));
        }

        @Test
        public void shouldReturnANotFoundWhenNotExistingUserWithSuchIdWhenGettingUserById() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

                long bookId = 4;

                Mockito.when(userRepository.findById(bookId)).thenReturn(Optional.ofNullable(null));

                mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + bookId)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void shouldUpdateUserWhenValidAndExistingUser() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

                Optional<User> foundUser = Optional.of(USERS[2]);

                User userToSave = new User(
                                3L, "User4", "LastName4",
                                "444444444", "user4@gmail.com",
                                "definitelyNotMyPass2");

                Mockito.when(userRepository.save(userToSave)).thenReturn(userToSave);
                Mockito.when(userRepository.findById(userToSave.getId())).thenReturn(foundUser);

                String stringifiedUser = objectWriter.writeValueAsString(userToSave);

                MockHttpServletRequestBuilder userPutRequest = MockMvcRequestBuilders.put("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(stringifiedUser);

                mockMvc.perform(userPutRequest)
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(userToSave.getName())));
        }

        @Test
        public void shouldReturnUnprocessableEntityIfTheUserIdIsNullOrShouldReturnBadRequestIfRequestBodyIsNotValidWhenUpdatingUser()
                        throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

                User userToSave1 = new User(
                                null, "User4", "LastName4",
                                "444444444", "user4@gmail.com",
                                "definitelyNotMyPass2");
                User userToSave2 = null;

                String stringifiedUser1 = objectWriter.writeValueAsString(userToSave1);
                String stringifiedUser2 = objectWriter.writeValueAsString(userToSave2);

                MockHttpServletRequestBuilder userPutRequest1 = MockMvcRequestBuilders.put("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(stringifiedUser1);
                MockHttpServletRequestBuilder userPutRequest2 = MockMvcRequestBuilders.put("/api/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(stringifiedUser2);

                mockMvc.perform(userPutRequest1)
                                .andExpect(status().isUnprocessableEntity());
                ;
                mockMvc.perform(userPutRequest2)
                                .andExpect(status().isBadRequest());
                ;
        }

        @Test
        public void shouldReturnANotFoundWhenUserDoesNotExistWhenUpdatingUser() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
                User userToUpdate = new User(
                                4L, "User4", "LastName4",
                                "444444444", "user4@gmail.com",
                                "definitelyNotMyPass2");

                Mockito.when(userRepository.findById(userToUpdate.getId())).thenReturn(Optional.ofNullable(null));

                String stringifiedUser = objectWriter.writeValueAsString(userToUpdate);

                mockMvc.perform(MockMvcRequestBuilders.put("/api/users/").contentType(MediaType.APPLICATION_JSON)
                                .content(stringifiedUser))
                                .andExpect(status().isNotFound());
        }

        @Test
        public void shouldDeleteUserByIdIfExisting() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
                User user2 = USERS[1];
                Mockito.when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));

                mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/" + user2.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());
        }

        @Test
        public void shouldReturnNotFoundIfNotExistingUserWhenDeletingUser() throws Exception {
                mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
                Long id = 4L;
                Mockito.when(userRepository.findById(any())).thenReturn(Optional.ofNullable(null));

                mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/" + id)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }
}
