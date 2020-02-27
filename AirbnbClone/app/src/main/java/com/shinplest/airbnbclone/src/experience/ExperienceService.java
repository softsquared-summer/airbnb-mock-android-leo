package com.shinplest.airbnbclone.src.experience;

import android.util.Log;

import com.shinplest.airbnbclone.src.experience.interfaces.ExperienceActivityView;
import com.shinplest.airbnbclone.src.experience.interfaces.ExperienceRetrofitInterface;
import com.shinplest.airbnbclone.src.experience.models.ExperienceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class ExperienceService {

    private ExperienceActivityView mExperienceActivityView;

    public ExperienceService(ExperienceActivityView experienceActivityView) {
        this.mExperienceActivityView = experienceActivityView;
    }

    void getExperienceInfo(int experienceNo){
        final ExperienceRetrofitInterface experienceRetrofitInterface = getRetrofit().create(ExperienceRetrofitInterface.class);
        experienceRetrofitInterface.getExperienceInfo(experienceNo).enqueue(new Callback<ExperienceResponse>() {
            @Override
            public void onResponse(Call<ExperienceResponse> call, Response<ExperienceResponse> response) {
                Log.d("testtesttest", call.request().url().toString());
                ExperienceResponse experienceResponse = response.body();
                mExperienceActivityView.getExperienceSuccess(experienceResponse.getResult(), experienceResponse.getCode(),experienceResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ExperienceResponse> call, Throwable t) {

            }
        });
    }
}
