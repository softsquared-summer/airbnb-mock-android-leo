package com.shinplest.airbnbclone.src.profile.interfaces;

import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

public interface ProfileActivityView {
    void validateSuccess(ProfileResponse profileResponse, int code, String message);

    void validateFailure(String message);
}
