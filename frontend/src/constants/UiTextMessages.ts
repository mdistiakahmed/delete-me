export class UiTextMessages {
    static notFoundPageMessages = {
        errorCode: '404',
        errorMessageHeader: "Look like you're lost",
        errorMessageBody: 'The page you are looking for not avaible!',
        goHomeButtonText: 'Go To Home',
    };

    static signInPageMessages = {
        title: 'Sign in',
        submitButtonText: 'Sign In',
        switchToSignUpPage: "Don't have an account? Sign Up",
        emailError: 'Please enter an email address',
        passwordError: 'Please enter password',
    };

    static signUpPageMessages = {
        title: 'Sign up',
        submitButtonText: 'Sign Up',
        switchToSignInPage: 'Already have an account? Sign in',
        emailInvalidMessage: 'Invalid Email',
        emailAbsentMessage: 'Email is Required',
        passwordMinLengthError: 'Minimum Digit 6',
        passwordMaxLengthError: 'Maximum Digit 20',
        passwordAbsentMessage: 'Password is Required',
        confirmPasswordErrorMessage: 'Password does not match',
    };
}
