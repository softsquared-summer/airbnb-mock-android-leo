package com.shinplest.airbnbclone.src.house.interfaces;

public interface HouseActivityView {
    void validateGetHouseSuccess(int code, String message);

    void validateGeHouseFailure(String message);
}
