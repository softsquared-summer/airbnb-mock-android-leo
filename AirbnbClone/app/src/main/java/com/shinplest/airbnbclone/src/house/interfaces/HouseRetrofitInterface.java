package com.shinplest.airbnbclone.src.house.interfaces;

import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.house.models.ReservationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HouseRetrofitInterface {
    @GET("/houses/{houseNo}")
    Call<HouseResponse> getHouseInfo(@Path("houseNo") int houseNo);

    @GET("houses/{houseNo}/calendar")
    Call<ReservationResponse> getReservationDate(@Path("houseNo") int houseNo);
}
