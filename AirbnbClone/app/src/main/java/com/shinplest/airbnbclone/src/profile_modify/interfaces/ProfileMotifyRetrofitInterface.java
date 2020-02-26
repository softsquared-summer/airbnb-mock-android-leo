package com.shinplest.airbnbclone.src.profile_modify.interfaces;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.profile_modify.models.RequestModifyProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface ProfileMotifyRetrofitInterface {
    @PUT("/users/{userNo}/profile")
    Call<DefaultResponse> putModifyProfile(@Body RequestModifyProfile modifyProfile);
}
