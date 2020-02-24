package com.shinplest.airbnbclone.src.main.interfaces;


public interface MainActivityView {
    void validateJwtLoginSuccess(String message, int userNo);

    void validateJwtLoginFailure(String message);
}

