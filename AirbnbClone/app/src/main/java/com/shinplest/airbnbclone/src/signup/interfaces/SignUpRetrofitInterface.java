package com.shinplest.airbnbclone.src.signup.interfaces;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SignUpRetrofitInterface {
    @GET("")
    Call<DefaultResponse> getSignUp(@Query("phone") String phonenum) {
        return null;
    }

}
