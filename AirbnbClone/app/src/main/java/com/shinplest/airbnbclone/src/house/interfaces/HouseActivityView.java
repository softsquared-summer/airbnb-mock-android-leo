package com.shinplest.airbnbclone.src.house.interfaces;

import com.shinplest.airbnbclone.src.house.models.HouseResponse;

public interface HouseActivityView {
    void getHouseSuccess(HouseResponse.Result houseResponseResult, int code, String message);

    void getHouseFailure(String message);
}
