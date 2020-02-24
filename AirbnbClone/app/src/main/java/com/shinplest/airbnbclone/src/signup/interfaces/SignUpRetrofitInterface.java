package com.shinplest.airbnbclone.src.signup.interfaces;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SignUpRetrofitInterface {
    @GET("/users")
    Call<DefaultResponse> getPhoneAvailable(@Query("phone") String phone);
}
