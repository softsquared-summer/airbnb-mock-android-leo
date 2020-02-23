package com.shinplest.airbnbclone.src.login.interfaces;

import com.shinplest.airbnbclone.src.login.models.JwtResponse;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/jwt")
    Call<JwtResponse> postJwt(@Body RequestJwt user);
}
