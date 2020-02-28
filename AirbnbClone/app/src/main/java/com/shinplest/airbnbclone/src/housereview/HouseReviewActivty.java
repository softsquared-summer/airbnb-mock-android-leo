package com.shinplest.airbnbclone.src.housereview;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.housereview.interfaces.HouseReviewActivityView;
import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

public class HouseReviewActivty extends BaseActivity implements HouseReviewActivityView {

    private HouseReviewResponse.Result mHouseReviewData;
    private int mHouseNo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_review);

        mHouseNo = getIntent().getIntExtra("houseNo", 1);

        tryGetHouseReviewData(mHouseNo);
    }

    void getUiSource(){

    }

    void updateUi(){

    }

    void tryGetHouseReviewData(int houseNo){
        final HouseReviewService houseReviewService = new HouseReviewService(this);
        showProgressDialog();
        houseReviewService.getHouseReviews(houseNo);
    }



    @Override
    public void getHouseReviewSuccess(HouseReviewResponse.Result houseReviewData, int code, String message) {
        hideProgressDialog();
        if (code == 100){
            showCustomToast("하우스 리뷰 가져오기 성공");
            mHouseReviewData = houseReviewData;
            updateUi();
        }
    }

    @Override
    public void getHouseReviewFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");

    }
}
