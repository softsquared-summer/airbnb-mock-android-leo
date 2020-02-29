package com.shinplest.airbnbclone.src.house.interfaces;

import com.shinplest.airbnbclone.src.house.models.HouseResponse;

public interface HouseActivityView {
    void getHouseSuccess(HouseResponse.Result houseResponseResult, int code, String message);

    void getHouseFailure(String message);

    void saveHouseSuccess(int code, String message);

    void saveHouseFailure(String message);

    void deleteHouseSuccess(int code, String message);

    void deleteHouseFailure(String message);

    void getReservationDateSuccess(String result, int code, String message);

    void getReservationDateFailure(String message);
}
