package com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SavelistRetrofitInterface {
    @GET("users/{userNo}/saveList")
    Call<SavelistResponse> getSavelist(@Path ("userNo") int userNo, @Query("filter") String filter, @Query("saveLocation") String saveLocation);
}

