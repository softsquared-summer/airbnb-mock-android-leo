package com.shinplest.airbnbclone.src.main.fragment_search.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AdvantureResponse {
    @SerializedName("Image")
    private ArrayList<Image> images;

    @SerializedName("juso")
    private String address;

    @SerializedName("title")
    private String titie;

    @SerializedName("evaluation")
    private String rate;

    @SerializedName("inclusion")
    private String include;

    private class Image {
        @SerializedName("image")
        private String imgUrl;

        public String getImgUrl() {
            return imgUrl;
        }
    }


    public ArrayList<Image> getImages() {
        return images;
    }

    public String getAddress() {
        return address;
    }

    public String getTitie() {
        return titie;
    }

    public String getRate() {
        return rate;
    }

    public String getInclude() {
        return include;
    }
}
