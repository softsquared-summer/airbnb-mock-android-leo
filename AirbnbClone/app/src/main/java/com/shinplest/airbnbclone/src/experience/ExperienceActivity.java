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

        tryGetExperienceInfo(1);
    }

    void tryGetExperienceInfo(int experienceNo){
        showProgressDialog();
        final ExperienceService experienceService = new ExperienceService(this);
        experienceService.getExperienceInfo(experienceNo);
    }

    @Override
    public void getExperienceSuccess(ExperienceResponse.Result experienceResult, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("경험 데이터 가져오기 성공");
        }
    }

    @Override
    public void getExperienceFailure(String message) {
        hideProgressDialog();
    }
}
