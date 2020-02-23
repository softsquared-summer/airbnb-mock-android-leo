package com.shinplest.airbnbclone.src.register.interfaces;


import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRetrofitInterface {
    @POST("/users")
    Call<DefaultResponse> postTest(@Body RequestRegister user);
}
