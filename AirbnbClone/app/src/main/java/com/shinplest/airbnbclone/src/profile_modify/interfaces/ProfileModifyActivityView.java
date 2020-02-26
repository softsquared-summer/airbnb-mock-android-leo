package com.shinplest.airbnbclone.src.profile_modify.interfaces;

public interface ProfileModifyActivityView {
    void validateProfileModifySuccess(String message, int code);

    void validateProfileModifyFailure(String message);
}
