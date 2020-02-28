package com.shinplest.airbnbclone.src.housereview;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.housereview.interfaces.HouseReviewActivityView;

public class HouseReviewActivty extends BaseActivity implements HouseReviewActivityView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_review);
    }
}
