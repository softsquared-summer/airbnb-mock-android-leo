package com.shinplest.airbnbclone.src.experience.interfaces;

import com.shinplest.airbnbclone.src.experience.models.ExperienceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExperienceRetrofitInterface {
    @GET("/experiences/{experienceNo}")
    Call<ExperienceResponse> getExperienceInfo(@Path("experienceNo") int experienceNo);
}
