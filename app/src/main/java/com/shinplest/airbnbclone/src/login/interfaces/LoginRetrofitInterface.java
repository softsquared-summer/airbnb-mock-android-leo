package com.shinplest.airbnbclone.src.login.interfaces;

import com.shinplest.airbnbclone.src.login.models.JwtResponse;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;
import com.shinplest.airbnbclone.src.main.models.UserNoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/jwt")
    Call<JwtResponse> postJwt(@Body RequestJwt user);

    //헤더에 자동으로 엑세스 토큰 추가해주기때문에..?
    @GET("/jwt")
    Call<UserNoResponse> getUserNo();
}
