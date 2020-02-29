package com.shinplest.airbnbclone.src.house.interfaces;

import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.house.models.RequestReserve;
import com.shinplest.airbnbclone.src.house.models.ReservationResponse;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HouseRetrofitInterface {
    @GET("/houses/{houseNo}")
    Call<HouseResponse> getHouseInfo(@Path("houseNo") int houseNo);

    @GET("houses/{houseNo}/calendar")
    Call<ReservationResponse> getReservationDate(@Path("houseNo") int houseNo);

    @POST("houses/{houseNo}/reservations")
    Call<DefaultResponse> postReserve(@Path("houseNo") int houseNo, @Body RequestReserve requestReserve);
}
