package com.shinplest.airbnbclone.src.search.models;

import com.google.gson.annotations.SerializedName;

public class SaveHouseRequest {
    @SerializedName("houseNo")
    private int houseNo;

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

}
