package com.shinplest.airbnbclone.src.house.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseResponse {
    @SerializedName("result")
    private ArrayList<Result> result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public class Result{
        @SerializedName("image")
        private ArrayList<Image> images;

        @SerializedName("info")
        private ArrayList<Info> info;
    }

    public class Image{
        @SerializedName("no")
        private int no;
        @SerializedName("image")
        private String imgUrl;
    }

    public class Info{
        @SerializedName("houseTitle")
        private String houseTitle;

        @SerializedName("houseLocation")
        private String houseLocation;

        @SerializedName("houseHost")
        private String houseHost;

        @SerializedName("houseType")
        private String houseType;

        @SerializedName("houseSummary")
        private String houseSummary;

        @SerializedName("houseInfo")
        private String houseInfo;

        @SerializedName("houseDetail")
        private String houseDetail;

        @SerializedName("minimumStay")
        private String minimumStay;

        @SerializedName("checkIn")
        private String checkIn;

        @SerializedName("checkOut")
        private String checkOut;

    }
}
//
//
//@SerializedName("")
//private String
