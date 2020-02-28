package com.shinplest.airbnbclone.src.housereview.interfaces;


import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

public interface HouseReviewActivityView {
    void getHouseReviewSuccess(HouseReviewResponse.Result houseReviewData, int code, String message);

    void getHouseReviewFailure(String message);
}
