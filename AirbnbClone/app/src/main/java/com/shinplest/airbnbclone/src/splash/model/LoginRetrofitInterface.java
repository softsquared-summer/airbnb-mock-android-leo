package com.shinplest.airbnbclone.src.splash.model;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @GET("/jwt")
    Call<DefaultResponse> getJwt();

   @POST("/jwt")
   Call<DefaultResponse>  postJwt();
}
