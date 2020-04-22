package com.shinplest.airbnbclone.src.login.interfaces;

public interface LoginActivityView {
    void validateLoginSuccess(int code, String message);

    void validateLoginFailure(String message);
}
