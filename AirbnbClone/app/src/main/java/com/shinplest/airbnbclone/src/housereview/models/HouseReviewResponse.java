package com.shinplest.airbnbclone.src.housereview.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HouseReviewResponse {
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
        @SerializedName("evaluation")
        private Evaluation evaluation;

        @SerializedName("reviews")
        private ArrayList<Review> reviews;

        public ArrayList<Review> getReviews() {
            return reviews;
        }

        public Evaluation getEvaluation() {
            return evaluation;
        }
    }

    public class Evaluation {
        @SerializedName("houseNo")
        private int houseNo;

        @SerializedName("starAvg")
        private String starAvg;

        @SerializedName("reviewCnt")
        private String reviewCnt;

        @SerializedName("starCheckin")
        private String  starCheckin;

        @SerializedName("starCommunication")
        private float starCommunication;

        @SerializedName("starAccuracy")
        private float starAccuracy;

        @SerializedName("starLocation")
        private float starLocation;

        @SerializedName("starClean")
        private float starClean;

        @SerializedName("starValue")
        private float starValue;

        public int getHouseNo() {
            return houseNo;
        }

        public String getStarAvg() {
            return starAvg;
        }

        public String getReviewCnt() {
            return reviewCnt;
        }

        public String getStarCheckin() {
            return starCheckin;
        }

        public float getStarCommunication() {
            return starCommunication;
        }

        public float getStarAccuracy() {
            return starAccuracy;
        }

        public float getStarLocation() {
            return starLocation;
        }

        public float getStarClean() {
            return starClean;
        }

        public float getStarValue() {
            return starValue;
        }
    }

    public class Review {
        @SerializedName("no")
        private int no;

        @SerializedName("guestNo")
        private int guestNo;

        @SerializedName("guestImg")
        private String guestImg;

        @SerializedName("guestName")
        private String guestName;

        @SerializedName("date")
        private String date;

        @SerializedName("reviewContent")
        private String reviewContent;

        @SerializedName("hostNo")
        private int hostNo;

        @SerializedName("hostReply")
        private String hostReply;

        public int getNo() {
            return no;
        }

        public int getGuestNo() {
            return guestNo;
        }

        public String getGuestImg() {
            return guestImg;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getDate() {
            return date;
        }

        public String getReviewContent() {
            return reviewContent;
        }

        public int getHostNo() {
            return hostNo;
        }

        public String getHostReply() {
            return hostReply;
        }
    }

}
