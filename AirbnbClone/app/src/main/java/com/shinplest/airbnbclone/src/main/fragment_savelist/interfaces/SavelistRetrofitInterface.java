package com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class SavelistRetrofitInterface {
    @GET("/saveList")
    Call<SavelistResponse> getSavelist(@Query("filter") String filter, @Query("saveList") String saveLocation);
}

