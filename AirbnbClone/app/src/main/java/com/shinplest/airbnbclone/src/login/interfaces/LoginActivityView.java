package com.shinplest.airbnbclone.src.login.interfaces;

public interface LoginActivityView {
    void validateLoginSuccess(boolean isSuccess, int code, String message);

    void validateLoginFailure(String message);
}
