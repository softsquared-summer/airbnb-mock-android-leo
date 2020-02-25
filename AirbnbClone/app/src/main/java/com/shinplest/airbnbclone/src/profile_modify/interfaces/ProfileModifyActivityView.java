package com.shinplest.airbnbclone.src.profile_modify.interfaces;

public interface ProfileModifyActivityView {
    void validateJwtLoginSuccess(String message, int code);

    void validateJwtLoginFailure(String message);
}
