package com.shinplest.airbnbclone.src.experience.interfaces;

import com.shinplest.airbnbclone.src.experience.models.ExperienceResponse;

public interface ExperienceActivityView {
    void getExperienceSuccess(ExperienceResponse.Result experienceResult, int code, String message);

    void getExperienceFailure(String message);
}
