package com.shinplest.airbnbclone.src.main.fragment_travel.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TravelRetrofitInterface {
    @GET("users/{userNo}/reservations")
    Call<ReservedResponse> getReservedResponse(@Path("userNo") int userNo);

}
