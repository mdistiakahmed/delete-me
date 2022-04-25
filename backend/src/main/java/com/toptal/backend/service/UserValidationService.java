package com.toptal.backend.service;

import com.toptal.backend.constants.ErrorMessageConstants;
import com.toptal.backend.data.model.User;
import com.toptal.backend.data.repository.UserRepository;
import com.toptal.backend.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidationService {
    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$", Pattern.CASE_INSENSITIVE);

    String regex = "(?=\\S+$).{6,20}$"; // no white space and length min 8, max 20
    public final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile(regex);
    @Autowired
    private UserRepository userRepository;

    public void validateCreation(User user) {
        checkIfInvalidData(user);
        if(checkIfUserExists(user.getEmail())) {
            throw new HttpException(HttpStatus.CONFLICT, ErrorMessageConstants.USER_ACCOUNT_ALREADY_EXISTS);
        }
    }

    public void checkIfInvalidData (User user) {
        String errorMsg = "";
        if(user == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, ErrorMessageConstants.USER_INVALID_DATA);
        }

        if(user.getEmail() == null || user.getEmail().trim().length()==0
                || !validateEmail(user.getEmail())) {
            errorMsg += ErrorMessageConstants.USER_INVALID_EMAIL;
        }

        if(user.getPassword() == null || user.getPassword().length() == 0
                || !validatePassword(user.getPassword())) {
            errorMsg += ErrorMessageConstants.USER_INVALID_PASSWORD;
        }

        if(errorMsg.length()>0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }

    public Boolean checkIfUserExists (String email) {
        User user = userRepository.findByEmail(email);
        return user == null ? false : true;
    }

    public void userModifyValidate (User user) {
        if(user == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, ErrorMessageConstants.USER_INVALID_DATA);
        }
        userModifyValidate(user.getEmail());
    }

    public void userModifyValidate (String username) {
        final String authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String errorMsg = "";
        if(username == null || username.length()==0) {
            errorMsg = ErrorMessageConstants.USER_INVALID_DATA;
        } else if(username.equalsIgnoreCase(authenticatedUser)) {
            errorMsg = ErrorMessageConstants.USER_OWN_DATA_MODIFY;
        }

        if(errorMsg.length()>0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, errorMsg);
        }
    }


    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.find();
    }

}
