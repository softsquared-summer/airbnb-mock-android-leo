package com.shinplest.airbnbclone.src.main.fragment_profile.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_profile.models.SimpleUserinfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileRetrofitInterface {
    @GET("/users/{userNo}/profile")
    Call<SimpleUserinfoResponse> getSimpleUserInfo(@Path("userNo") int userNo);

}
