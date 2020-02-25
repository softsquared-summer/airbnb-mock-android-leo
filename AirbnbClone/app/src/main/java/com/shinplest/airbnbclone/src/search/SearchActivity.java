package com.shinplest.airbnbclone.src.search;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

public class SearchActivity extends BaseActivity {

    private Button testButton;
    private LinearLayout mLlSearchTopContainer;
    private ConstraintLayout mClSearchHouseContainer;

    private TextView mTvCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTvCancel = findViewById(R.id.tv_search_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //검색화면 교체해주는 부분
        mLlSearchTopContainer = findViewById(R.id.ll_search_top_container);
        mClSearchHouseContainer = findViewById(R.id.cl_search_house_container);
        testButton = findViewById(R.id.search_button_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlSearchTopContainer.setVisibility(View.GONE);
                mClSearchHouseContainer.setVisibility(View.VISIBLE);
            }
        });
    }
}
