package com.shinplest.airbnbclone.src.housereview.interfaces;

import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HouseReviewRetrofitInterface {
    @GET("/houses/{houseNo}/review")
    Call<HouseReviewResponse> getHouseReviews(@Path("houseNo") int houseNo);
}
