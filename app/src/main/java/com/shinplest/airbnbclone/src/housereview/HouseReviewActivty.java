package com.shinplest.airbnbclone.src.housereview;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private LinearLayout mLlReviewContainer;
    private HouseReviewAdapter mHouseReviewAdapter;
    private TextView mTvStarAvg, mTvReviewCnt;

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

    void getUiSource() {
        mRvHouseReviews = findViewById(R.id.rv_house_review);
        mLlReviewContainer = findViewById(R.id.ll_house_review_container);
        mTvStarAvg = findViewById(R.id.tv_house_review_star_avg);
        mTvReviewCnt = findViewById(R.id.tv_house_review_review_cnt);
    }

    void updateUi() {
        mHouseReviewAdapter.notifyDataSetChanged();
        mTvStarAvg.setText(mHouseReviewData.getEvaluation().getStarAvg());
        mTvReviewCnt.setText(mHouseReviewData.getEvaluation().getReviewCnt());
    }

    void tryGetHouseReviewData(int houseNo) {
        final HouseReviewService houseReviewService = new HouseReviewService(this);
        showProgressDialog();
        houseReviewService.getHouseReviews(houseNo);
    }

    @Override
    public void getHouseReviewSuccess(HouseReviewResponse.Result houseReviewData, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            //리뷰없으면 업에이트안함
            if (houseReviewData.getReviews().size() == 0) {
                mLlReviewContainer.setVisibility(View.GONE);
                mRvHouseReviews.setVisibility(View.GONE);
                showCustomToast("이 숙소는 리뷰가 아직 없습니다.");
            } else {
                mHouseReviewData = houseReviewData;
                mHouseReviewList.addAll(houseReviewData.getReviews());
                updateUi();
            }
        }
    }

    @Override
    public void getHouseReviewFailure(String message) {
        hideProgressDialog();
        mLlReviewContainer.setVisibility(View.GONE);
        mRvHouseReviews.setVisibility(View.GONE);
        showCustomToast(message);

    }
}
