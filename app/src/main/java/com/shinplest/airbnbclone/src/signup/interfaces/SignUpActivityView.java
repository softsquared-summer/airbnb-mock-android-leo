package com.shinplest.airbnbclone.src.signup.interfaces;

public interface SignUpActivityView {
    void validateSignUpSuccess(String message, int code);

    void validateSignUpFailure(String message);
}
