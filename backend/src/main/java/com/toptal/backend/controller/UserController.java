package com.toptal.backend.controller;

import com.toptal.backend.constants.AuthorityConstants;
import com.toptal.backend.constants.RestApiConstants;
import com.toptal.backend.controller.dto.request.UserCredentialParam;
import com.toptal.backend.controller.dto.response.DeleteCountResponse;
import com.toptal.backend.controller.dto.response.TokenResponse;
import com.toptal.backend.controller.dto.response.UserPageListResponse;
import com.toptal.backend.data.model.User;
import com.toptal.backend.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Api(tags = "users", description = "Api for user management")
public class UserController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @RequestMapping(value = "login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse login(@RequestBody UserCredentialParam userCredentialParam) {
        String token = userService.login(modelMapper.map(userCredentialParam, User.class));
        return new TokenResponse(token);
    }

    @RequestMapping(value = "signup",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenResponse signup(@RequestBody UserCredentialParam userCredentialParam) {
        userService.createUser(modelMapper.map(userCredentialParam, User.class));
        String token = userService.authenticateAndGenerateToken(userCredentialParam.getEmail(), userCredentialParam.getPassword());
        return new TokenResponse(token);
    }

    @RequestMapping(value = "users",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UserPageListResponse getAllUser(
            @RequestParam(value = "pageNo", defaultValue = RestApiConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = RestApiConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize
    ) {
        Page<User> userPage = userService.getAllUser(pageNo, pageSize);
        return UserPageListResponse.from(userPage);
    }

    @RequestMapping(value = "users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthorityConstants.ADMIN})
    public User createUser(@RequestBody UserCredentialParam userCredentialParam) {
        User createdUser = userService.createUser(modelMapper.map(userCredentialParam, User.class));
        return createdUser;
    }


    @RequestMapping(value = "users",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthorityConstants.ADMIN})
    public User updateUser(@RequestBody UserCredentialParam param) {
        User updatedUser = userService.updateUser(modelMapper.map(param, User.class));
        return updatedUser;
    }

    @RequestMapping(value = "users/{username}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({AuthorityConstants.ADMIN})
    public DeleteCountResponse deleteUser(@PathVariable String username) {
        int deleteCount = userService.deleteUser(username);
        return new DeleteCountResponse(deleteCount);
    }
}