package com.toptal.backend.service;

import com.toptal.backend.security.JwtUtil;
import com.toptal.backend.constants.ErrorMessageConstants;
import com.toptal.backend.data.model.User;
import com.toptal.backend.data.repository.AuthorityRepository;
import com.toptal.backend.data.repository.UserRepository;
import com.toptal.backend.exception.HttpException;
import com.toptal.backend.testutil.BaseTestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class UserServiceTest extends BaseTestCase {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private AuthorityRepository mockAuthorityRepository;

    @Mock
    private BCryptPasswordEncoder mockBcryptEncoder;

    @Mock
    private UserValidationService mockUserValidationService;

    @Mock
    private AuthenticationManager mockAuthenticationManager;

    @Mock
    private JwtUtil mockJwtUtil;

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService();
        ReflectionTestUtils.setField(userService, "userRepository", mockUserRepository);
        ReflectionTestUtils.setField(userService, "authorityRepository", mockAuthorityRepository);
        ReflectionTestUtils.setField(userService, "bcryptEncoder", mockBcryptEncoder);
        ReflectionTestUtils.setField(userService, "userValidationService", mockUserValidationService);
        ReflectionTestUtils.setField(userService, "authenticationManager", mockAuthenticationManager);
        ReflectionTestUtils.setField(userService, "jwtUtil", mockJwtUtil);
    }

    @Test
    public void testUpdate() {
        User userToUpdate = new User();
        userToUpdate.setEmail("a@gmail.com");
        when(mockUserRepository.findByEmail(Mockito.any())).thenReturn(userToUpdate);

        userService.updateUser(userToUpdate);
        Mockito.verify(mockUserValidationService).userModifyValidate((User) Mockito.any());
        Mockito.verify(mockUserRepository).findByEmail(Mockito.any());
        Mockito.verify(mockUserRepository).save(Mockito.any());
    }

    @Test
    public void testUpdateInvalidUser() {
        User userToUpdate = new User();
        doThrow(new HttpException(HttpStatus.BAD_REQUEST, ErrorMessageConstants.USER_INVALID_DATA)).when(mockUserValidationService).userModifyValidate(userToUpdate);

        assertThatThrownBy(() -> { userService.updateUser(userToUpdate); })
                .hasMessage(ErrorMessageConstants.USER_INVALID_DATA);

        Mockito.verify(mockUserValidationService).userModifyValidate((User) Mockito.any());
        Mockito.verifyNoInteractions(mockUserRepository);
    }

    @Test
    public void testUpdateUserNotPresent() {
        User userToUpdate = new User();

        assertThatThrownBy(() -> { userService.updateUser(userToUpdate); })
                .hasMessage(ErrorMessageConstants.USER_ACCOUNT_NOT_EXISTS);

        Mockito.verify(mockUserValidationService).userModifyValidate((User) Mockito.any());
        Mockito.verify(mockUserRepository).findByEmail(Mockito.any());
        Mockito.verifyNoMoreInteractions(mockUserRepository);
    }

    @Test
    public void testDelete() {
        String email = "abc@gmail.com";
        userService.deleteUser(email);
        Mockito.verify(mockUserValidationService).userModifyValidate(email);
        Mockito.verify(mockAuthorityRepository).deleteUserAuthorities(email);
        Mockito.verify(mockUserRepository).deleteByEmail(email);
    }

    @Test
    public void testDeleteWithInvalidEmail() {
        String email = "abc";
        doThrow(new HttpException(HttpStatus.BAD_REQUEST, "test error")).when(mockUserValidationService).userModifyValidate(email);

        assertThatThrownBy(() -> { userService.deleteUser(email); }).hasMessage("test error");
        Mockito.verify(mockUserValidationService).userModifyValidate(email);
        Mockito.verifyNoInteractions(mockAuthorityRepository);
        Mockito.verifyNoInteractions(mockUserRepository);

    }
}
