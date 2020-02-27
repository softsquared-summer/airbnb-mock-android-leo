package com.shinplest.airbnbclone.src.experience;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.experience.interfaces.ExperienceActivityView;
import com.shinplest.airbnbclone.src.experience.models.ExperienceResponse;
import com.shinplest.airbnbclone.src.general.BaseActivity;

public class ExperienceActivity extends BaseActivity implements ExperienceActivityView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
    }

    @Override
    public void getExperienceSuccess(ExperienceResponse.Result experienceResult, int code, String message) {

    }

    @Override
    public void getExperienceFailure(String message) {

    }
}
