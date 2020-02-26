package com.shinplest.airbnbclone.src.house.interfaces;

import com.shinplest.airbnbclone.src.house.models.HouseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HouseRetrofitInterface {
    @GET("/houses/{houseNo}")
    Call<HouseResponse> getHouseInfo(@Path("houseNo") int houseNo);
}
