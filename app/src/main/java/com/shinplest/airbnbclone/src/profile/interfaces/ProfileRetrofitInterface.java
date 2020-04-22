package com.shinplest.airbnbclone.src.profile.interfaces;

import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileRetrofitInterface {
    @GET("/users/{userNo}/profile")
    Call<ProfileResponse> getProfile(@Path("userNo") int userNo);
}
