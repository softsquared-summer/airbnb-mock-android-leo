package com.shinplest.airbnbclone.src.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    //뷰페이져 테스트
    ViewPager mViewPager;

    HouseViewPageAdatper houseViewPageAdatper;

    //집데이터 배열
    private ArrayList<SimpleHouseInfoResponse.Result> mHouseDataList;

    //리스트뷰 배열
    private ArrayList<ExistLocationResponse.Result> mLocationList = null;
    private SearchLocationListAdaper mSearchLocationListAdaper;

    private Button testButton;
    private LinearLayout mLlSearchTopContainer;
    private ConstraintLayout mClSearchHouseContainer;
    private TextView mTvCancel;
    private RecyclerView mRvHouses;
    private EditText mEtSearchLocation;
    private ListView mLvSearchLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getUiSource();

        //생성해주고 할당만 함
        mLocationList = new ArrayList<ExistLocationResponse.Result>();
        mSearchLocationListAdaper = new SearchLocationListAdaper(mLocationList, SearchActivity.this);
        mLvSearchLocation.setAdapter(mSearchLocationListAdaper);

        //검색리스너가 검색을 할때마다 리스트 가져온다.
        mEtSearchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //텍스트가 변할때 마다 검색리스트를 불러옴
            //바뀌면 기존 배열 비우고, 새로운 배열 저장하고 어댑터에 알려준다.
            @Override
            public void afterTextChanged(Editable s) {
                String text = mEtSearchLocation.getText().toString();
                String text2 = s.toString();
                tryGetExistLocation(text);
            }
        });


        //하우스 데이터 가져옴
        tryGetSimpleHouseInfo();

        //검색 취소 해주는 부분
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //검색화면 교체해주는 부분
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlSearchTopContainer.setVisibility(View.GONE);
                mClSearchHouseContainer.setVisibility(View.VISIBLE);
            }
        });

    }


    private void tryGetExistLocation(String searchWord) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.getExistLocationList(searchWord);
    }

    private void getUiSource() {
        mTvCancel = findViewById(R.id.tv_search_cancel);
        mLlSearchTopContainer = findViewById(R.id.ll_search_top_container);
        mClSearchHouseContainer = findViewById(R.id.cl_search_house_container);
        testButton = findViewById(R.id.search_button_test);
        mEtSearchLocation = findViewById(R.id.et_search_location);
        mLvSearchLocation = findViewById(R.id.lv_search_location_list);
    }

    private void makeHouseRecyclerView() {
        //하우스 리사이클러
        mRvHouses = findViewById(R.id.rv_search_houses);
        mRvHouses.setHasFixedSize(true);
        mRvHouses.setLayoutManager(new LinearLayoutManager(this));
        mRvHouses.setAdapter(new HousesAdapter(this, mHouseDataList));
    }

    private void tryGetSimpleHouseInfo() {
        final SearchHouseService searchHouseService = new SearchHouseService(this);
        showProgressDialog();
        searchHouseService.getSimpleHouseInfo();
        Log.d("network", "tryGetSimpleHouseInfo: getsimplehouseinfo");

    }


    //로케이션을 받아오면 로케이션 arraylist를 저장
    @Override
    public void searchSuccess(ArrayList<ExistLocationResponse.Result> existLocationList, String code, String message) {
        //검색결과가 없을때 제외하고 바꿔주고 바꼈다고 알려줌
        if (existLocationList.size() != 0) {
            mLocationList = existLocationList;
            Log.d("test", mLocationList.get(0).getExistLocation());
            mSearchLocationListAdaper.notifyDataSetChanged();
        }
        hideProgressDialog();
    }

    @Override
    public void searchFailure() {
        hideProgressDialog();
    }

    @Override
    public void searchHouseSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse) {
        hideProgressDialog();
        //데이터 가져옴
        mHouseDataList = simpleHouseInfoResponse.getResult();
        makeHouseRecyclerView();
        Log.d("network", "validateSearchSuccess: " + mHouseDataList.get(2).getHouseName());

//        mHouseDataList.addAll(mHouseDataList);
    }

    @Override
    public void searchHouseFailure(String message) {
        hideProgressDialog();
    }

}
