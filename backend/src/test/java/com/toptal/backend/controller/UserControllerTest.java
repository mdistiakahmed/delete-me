package com.toptal.backend.controller;

import com.toptal.backend.controller.dto.request.UserCredentialParam;
import com.toptal.backend.controller.dto.response.UserPageListResponse;
import com.toptal.backend.data.model.User;
import com.toptal.backend.data.repository.UserRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.toptal.backend.testutil.TestUtil.convertJsonStringToObject;
import static com.toptal.backend.testutil.TestUtil.convertObjectToJsonBytes;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private final String VALID_USER = "admin@gmail.com";

    @Autowired private MockMvc mockMvc;


    @WithMockUser(username = "bbb@gmail.com")
    @Test
    public void testGetAllUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String responseContent = mvcResult.getResponse().getContentAsString();
        UserPageListResponse userPageListResponse = convertJsonStringToObject(responseContent, UserPageListResponse.class);
        assertEquals(userPageListResponse.getTotalElements(), 1);

    }

    @Test
    public void testSignUpSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("test@gmail.com","A#dmin@#123"))))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content.contains("token"), true);

    }

    @Test
    public void testSignUpDuplicateUsername() throws Exception {
        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("admin@gmail.com","Admin@123"))))
                .andExpect(status().isConflict());

    }

    @Test
    public void testSignUpInvalidData() throws Exception {
        mockMvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("abc","abc"))))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testSignInSuccess() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("admin@gmail.com", "Admin@550"))))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content.contains("token"), true);
    }

    @Test
    public void testSignInFailureForNoAccount() throws Exception {
        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("test@gamil.com","Admin$666"))))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testSignInFailureForInvalidInput() throws Exception {
        mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(convertObjectToJsonBytes(new UserCredentialParam("",""))))
                .andExpect(status().isBadRequest());

    }

}
