package com.shinplest.airbnbclone.src.search;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    //뷰페이져 테스트
    ViewPager mViewPager;
    String url[] = {"https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg"};
    CustomPageAdapter customPageAdapter;

    //집데이터 배열
    private ArrayList<SimpleHouseInfoResponse.Result> mHouseDataList;

    private Button testButton;
    private LinearLayout mLlSearchTopContainer;
    private ConstraintLayout mClSearchHouseContainer;

    private TextView mTvCancel;

    private RecyclerView mRvHouses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //하우스 데이터 가져옴
        tryGetSimpleHouseInfo();

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

    private void makeHouseRecyclerView() {
        //하우스 리사이클러
        mRvHouses = findViewById(R.id.rv_search_houses);
        mRvHouses.setHasFixedSize(true);
        mRvHouses.setLayoutManager(new LinearLayoutManager(this));
        mRvHouses.setAdapter(new HousesAdapter(this,mHouseDataList));
    }

    private void tryGetSimpleHouseInfo() {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.getSimpleHouseInfo();
        Log.d("network", "tryGetSimpleHouseInfo: getsimplehouseinfo");
    }

    @Override
    public void validateSearchSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse) {
        hideProgressDialog();
        //데이터 가져옴
        mHouseDataList = simpleHouseInfoResponse.getResult();
        makeHouseRecyclerView();
        Log.d("network", "validateSearchSuccess: " + mHouseDataList.get(2).getHouseName());
    }

    @Override
    public void validateSearchFailure(String message) {
        hideProgressDialog();
    }
}
