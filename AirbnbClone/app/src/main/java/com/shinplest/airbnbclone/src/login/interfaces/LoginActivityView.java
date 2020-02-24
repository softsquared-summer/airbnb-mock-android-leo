package com.shinplest.airbnbclone.src.login.interfaces;

public interface LoginActivityView {
    void validateLoginSuccess(int code);

    void validateLoginFailure(String message);
}
