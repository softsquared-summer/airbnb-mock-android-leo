package com.shinplest.airbnbclone.src.main.fragment_travel.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReservationResponse {

    @SerializedName("result")
    private Result result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public Result getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public class Result {
        @SerializedName("reservationList")
        private ArrayList<ReservationList> reservationList;

        @SerializedName("pastReservationList")
        private ArrayList<PastReservationList> pastReservationList;

        public ArrayList<ReservationList> getReservationList() {
            return reservationList;
        }

        public ArrayList<PastReservationList> getPastReservationList() {
            return pastReservationList;
        }
    }

    public class ReservationList {
        @SerializedName("reservationNo")
        private int reservationNo;

        @SerializedName("no")
        private int no;

        @SerializedName("category")
        private String category;

        @SerializedName("hostImage")
        private String hostImage;

        @SerializedName("location")
        private String location;

        @SerializedName("name")
        private String name;

        @SerializedName("guestCnt")
        private int guestCnt;

        @SerializedName("imageUrl")
        private String imageUrl;

        @SerializedName("date")
        private String date;

        @SerializedName("createdAt")
        private String createdAt;

        public int getReservationNo() {
            return reservationNo;
        }

        public int getNo() {
            return no;
        }

        public String getCategory() {
            return category;
        }

        public String getHostImage() {
            return hostImage;
        }

        public String getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public int getGuestCnt() {
            return guestCnt;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getDate() {
            return date;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

    public class PastReservationList {

        @SerializedName("reservationNo")
        private int reservationNo;

        @SerializedName("no")
        private int no;

        @SerializedName("category")
        private String category;

        @SerializedName("hostImage")
        private String hostImage;

        @SerializedName("location")
        private String location;

        @SerializedName("name")
        private String name;

        @SerializedName("guestCnt")
        private int guestCnt;

        @SerializedName("imageUrl")
        private String imageUrl;

        @SerializedName("date")
        private String date;

        @SerializedName("createdAt")
        private String createdAt;

        public int getReservationNo() {
            return reservationNo;
        }

        public int getNo() {
            return no;
        }

        public String getCategory() {
            return category;
        }

        public String getHostImage() {
            return hostImage;
        }

        public String getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public int getGuestCnt() {
            return guestCnt;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getDate() {
            return date;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }


}
