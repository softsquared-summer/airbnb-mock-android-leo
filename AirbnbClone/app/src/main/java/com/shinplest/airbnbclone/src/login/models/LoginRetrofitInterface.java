package com.shinplest.airbnbclone.src.login.models;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.UserInfo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @GET("/jwt")
    Call<DefaultResponse> getJwt();

   @POST("/jwt")
   Call<DefaultResponse>  postJwt(@Body UserInfo user);
}
