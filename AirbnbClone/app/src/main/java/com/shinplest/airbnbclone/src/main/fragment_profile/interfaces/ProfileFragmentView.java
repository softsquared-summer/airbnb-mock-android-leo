package com.shinplest.airbnbclone.src.main.fragment_profile.interfaces;

public interface ProfileFragmentView {
    void validateSuccess(String profileImgUrl, String firstName, int code, String message);

    void validateFailure(String message);
}
