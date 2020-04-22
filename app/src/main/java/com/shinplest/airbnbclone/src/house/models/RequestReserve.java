package com.shinplest.airbnbclone.src.house.models;

import com.google.gson.annotations.SerializedName;

public class RequestReserve {
    @SerializedName("userNo")
    private int userNo;

    @SerializedName("checkIn")
    private String checkIn;

    @SerializedName("checkOut")
    private String checkOut;

    @SerializedName("guestCnt")
    private int guestCnt;

    @SerializedName("totalPrice")
    private int totalPrice;

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public void setGuestCnt(int guestCnt) {
        this.guestCnt = guestCnt;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
