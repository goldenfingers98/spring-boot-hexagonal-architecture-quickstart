package tn.ksoftwares.auth.rest.controller.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.rest.config.TestConfiguration;

@SpringBootTest(classes = TestConfiguration.class)
@AutoConfigureMockMvc
class UserControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setupTestAddUser(TestInfo testInfo)
            throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException,
            MalformedFieldException {
        user = new User();
        if (testInfo.getDisplayName().equals("testAddUserBadRequest()")) {
            Mockito.doThrow(DomainConstraintViolationException.class).when(userService).saveUser(Mockito.any());
        } else if (testInfo.getDisplayName().equals("testAddUserConflict()")) {
            Mockito.doThrow(EmailAlreadyUsedException.class).when(userService).saveUser(Mockito.any());
        } else if (testInfo.getDisplayName().equals("testAddUserOk()")) {
            Mockito.doNothing().when(userService).saveUser(Mockito.any());
        }
    }

    @Test
    void testAddUserOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/users/add/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(user.toString())).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddUserBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/users/add/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(user.toString())).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testAddUserConflict() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/users/add/")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(user.toString())).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    void testHealthCheck() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/users/")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
