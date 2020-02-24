package com.shinplest.airbnbclone.src.main.fragment_profile.interfaces;

public interface ProfileFragmentView {
    void validateSuccess(String last_name, String first_name, int code, String message);

    void validateFailure(String message);
}
