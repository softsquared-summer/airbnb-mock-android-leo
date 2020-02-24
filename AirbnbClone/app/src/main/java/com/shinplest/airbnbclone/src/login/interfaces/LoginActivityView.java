package com.shinplest.airbnbclone.src.login.interfaces;

public interface LoginActivityView {
    void validateLoginSuccess(String message, int code);

    void validateLoginFailure(String message);
}
