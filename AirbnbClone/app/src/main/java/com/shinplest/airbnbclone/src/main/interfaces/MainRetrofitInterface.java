package com.shinplest.airbnbclone.src.main.interfaces;


import com.shinplest.airbnbclone.src.login.models.JwtResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainRetrofitInterface {
    @GET("/jwt")
    Call<JwtResponse> getTest();

}
