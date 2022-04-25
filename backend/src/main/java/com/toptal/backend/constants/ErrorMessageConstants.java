package com.toptal.backend.constants;

public interface ErrorMessageConstants {
    // user error messages
    String USER_ACCOUNT_ALREADY_EXISTS = "Account with this email already exists";
    String USER_ACCOUNT_NOT_EXISTS = "Account with this email does not exists";
    String USER_INVALID_DATA = "User data is invalid";
    String USER_INVALID_EMAIL = "Email is not valid.";
    String USER_INVALID_PASSWORD = "Password must be between 6 to 20 characters long.";
    String USER_OWN_DATA_MODIFY = "Can't modify own data";

    // Authentication and authorization error messages
    String ACCESS_DENIED_MESSAGE = "You do not have permission to access this resource";
    String UNAUTHORIZED_USER_MESSAGE = "Use valid email and password";
}
