package com.shinplest.airbnbclone.src.register.interfaces;


import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.UserInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RegisterRetrofitInterface {
    //    @GET("/test")
    @GET("/jwt")
    Call<DefaultResponse> getTest();

    @GET("/test/{number}")
    Call<DefaultResponse> getTestPathAndQuery(
            @Path("number") int number,
            @Query("content") final String content
    );

    @POST("/users")
    Call<DefaultResponse> postTest(@Body UserInfo user);
}
