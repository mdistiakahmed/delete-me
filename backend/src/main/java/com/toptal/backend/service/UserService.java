package com.toptal.backend.service;

import com.toptal.backend.security.JwtUtil;
import com.toptal.backend.constants.AuthorityConstants;
import com.toptal.backend.constants.ErrorMessageConstants;
import com.toptal.backend.data.model.User;
import com.toptal.backend.data.repository.AuthorityRepository;
import com.toptal.backend.data.repository.UserRepository;
import com.toptal.backend.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    private UserValidationService userValidationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(User user) {
        userValidationService.checkIfInvalidData(user);
        return authenticateAndGenerateToken(user.getEmail(), user.getPassword());
    }

    public Page<User> getAllUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<User> users = userRepository.findAll(pageable);

        return users;
    }

    @Transactional
    public User createUser(User user) {
        userValidationService.validateCreation(user);

        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User createdUser =  userRepository.save(user);
        if(createdUser == null) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong, can't create user");
        }
        authorityRepository.insertUserAuthorities(user.getEmail(), AuthorityConstants.USER);
        return createdUser;
    }

    public User updateUser(User userToUpdate) {
        userValidationService.userModifyValidate(userToUpdate);

        User user = userRepository.findByEmail(userToUpdate.getEmail());
        if(user == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, ErrorMessageConstants.USER_ACCOUNT_NOT_EXISTS);
        }
        user.setPassword(userToUpdate.getPassword());
        User updatedUser =  userRepository.save(user);

        return updatedUser;

    }

    @Transactional
    public int deleteUser(String email) {
        userValidationService.userModifyValidate(email);
        int deleteCount = authorityRepository.deleteUserAuthorities(email);
        userRepository.deleteByEmail(email);

        return deleteCount;
    }

    public String authenticateAndGenerateToken(String username, String password) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        return jwtUtil.generateToken(authentication);
    }
}
