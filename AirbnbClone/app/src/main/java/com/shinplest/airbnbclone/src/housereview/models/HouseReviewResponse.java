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
        private int starAvg;

        @SerializedName("reviewCnt")
        private String reviewCnt;

        @SerializedName("starCheckin")
        private int starCheckin;

        @SerializedName("starCommunication")
        private int starCommunication;

        @SerializedName("starAccuracy")
        private int starAccuracy;

        @SerializedName("starLocation")
        private int starLocation;

        @SerializedName("starClean")
        private int starClean;

        @SerializedName("starValue")
        private int starValue;

        public int getHouseNo() {
            return houseNo;
        }

        public int getStarAvg() {
            return starAvg;
        }

        public String getReviewCnt() {
            return reviewCnt;
        }

        public int getStarCheckin() {
            return starCheckin;
        }

        public int getStarCommunication() {
            return starCommunication;
        }

        public int getStarAccuracy() {
            return starAccuracy;
        }

        public int getStarLocation() {
            return starLocation;
        }

        public int getStarClean() {
            return starClean;
        }

        public int getStarValue() {
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
