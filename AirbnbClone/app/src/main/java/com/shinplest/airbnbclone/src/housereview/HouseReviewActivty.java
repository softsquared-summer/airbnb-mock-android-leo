package com.shinplest.airbnbclone.src.housereview;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.housereview.interfaces.HouseReviewActivityView;
import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

import java.util.ArrayList;

public class HouseReviewActivty extends BaseActivity implements HouseReviewActivityView {

    private HouseReviewResponse.Result mHouseReviewData;
    private ArrayList<HouseReviewResponse.Review> mHouseReviewList;
    private int mHouseNo;

    private RecyclerView mRvHouseReviews;
    private HouseReviewAdapter mHouseReviewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_review);
        getUiSource();
        mHouseNo = getIntent().getIntExtra("houseNo", 1);


        mHouseReviewList = new ArrayList<>();
        mRvHouseReviews.setHasFixedSize(true);
        mRvHouseReviews.setLayoutManager(new LinearLayoutManager(this));
        mHouseReviewAdapter = new HouseReviewAdapter(mHouseReviewList);
        mRvHouseReviews.setAdapter(mHouseReviewAdapter);

        tryGetHouseReviewData(mHouseNo);
    }

    void getUiSource(){
        mRvHouseReviews = findViewById(R.id.rv_house_review);
    }

    void updateUi(){
        mHouseReviewAdapter.notifyDataSetChanged();
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

            mHouseReviewList.addAll(houseReviewData.getReviews());
            Log.d("hello", "getHouseReviewSuccess: "+mHouseReviewList.get(0).getGuestName());
            updateUi();
        }
    }

    @Override
    public void getHouseReviewFailure(String message) {
        hideProgressDialog();
        Log.d("hello", "fial");
        showCustomToast("실패");

    }
}
