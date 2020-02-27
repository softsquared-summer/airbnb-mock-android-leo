package com.shinplest.airbnbclone.src.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.shinplest.airbnbclone.src.search.adapters.HouseViewPageAdatper;
import com.shinplest.airbnbclone.src.search.adapters.HousesAdapter;
import com.shinplest.airbnbclone.src.search.adapters.SearchLocationListAdaper;
import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    //뷰페이져 테스트
    ViewPager mViewPager;

    HouseViewPageAdatper houseViewPageAdatper;

    //집데이터 배열
    private ArrayList<SimpleHouseInfoResponse.Result> mHouseDataList;

    //리스트뷰 배열
    private ArrayList<String> mLocationList = null;
    private SearchLocationListAdaper mSearchLocationListAdaper;

    private Button testButton;
    private LinearLayout mLlSearchTopContainer, mLlSearchBar;
    private ConstraintLayout mClSearchHouseContainer;
    private TextView mTvCancel, mTvSearchLocation;
    private RecyclerView mRvHouses;
    private EditText mEtSearchLocation;
    private ListView mLvSearchLocation;
    private ImageView mIvEraseInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getUiSource();

        //생성해주고 할당만 함
        mLocationList = new ArrayList<String>();
/*        mLocationList.add("괌");
        mLocationList.add("파리");
        mLocationList.add("서울");*/


        mSearchLocationListAdaper = new SearchLocationListAdaper(mLocationList, SearchActivity.this);
        mLvSearchLocation.setAdapter(mSearchLocationListAdaper);
        //아이템이 클릭됐을때 그 워드로 검색요청을 보내준다.
        mLvSearchLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hideKeyboard();
                TextView textView = view.findViewById(R.id.tv_search_list_location);
                String searchWord = textView.getText().toString();
                showCustomToast(searchWord);
                //하우스 데이터 가져옴
                tryGetSimpleHouseInfo(searchWord);
                mLlSearchTopContainer.setVisibility(View.GONE);
                mClSearchHouseContainer.setVisibility(View.VISIBLE);
                mTvSearchLocation.setText(searchWord + " 숙소");
            }
        });

        //검색리스너가 검색을 할때마다 리스트 가져온다.
        mEtSearchLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //텍스트가 변할때 마다 검색리스트를 불러옴
            @Override
            public void afterTextChanged(Editable s) {
                String searchWord = s.toString();
                tryGetExistLocation(searchWord);
            }
        });

        //검색 취소 해주는 부분
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //상단 검색바 누를시 다시 검색으로 바꿔줌
        mLlSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLlSearchTopContainer.setVisibility(View.VISIBLE);
                mClSearchHouseContainer.setVisibility(View.GONE);
            }
        });

        mIvEraseInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearchLocation.setText("");
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
        mTvSearchLocation = findViewById(R.id.tv_search_house_location);
        mLlSearchTopContainer = findViewById(R.id.ll_search_top_container);
        mClSearchHouseContainer = findViewById(R.id.cl_search_house_container);
        mEtSearchLocation = findViewById(R.id.et_search_location);
        mLvSearchLocation = findViewById(R.id.lv_search_location_list);
        mLlSearchBar = findViewById(R.id.ll_search_search_bar);
        mIvEraseInput = findViewById(R.id.iv_search_erase_input);
    }

    private void makeHouseRecyclerView() {
        //하우스 리사이클러
        mRvHouses = findViewById(R.id.rv_search_houses);
        mRvHouses.setHasFixedSize(true);
        mRvHouses.setLayoutManager(new LinearLayoutManager(this));
        mRvHouses.setAdapter(new HousesAdapter(this, mHouseDataList));
    }

    private void tryGetSimpleHouseInfo(String searchWord) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.getSimpleHouseInfo(searchWord);
        Log.d("network", "tryGetSimpleHouseInfo: getsimplehouseinfo");

    }


    //로케이션을 받아오면 로케이션 arraylist를 저장
    @Override
    public void searchSuccess(ArrayList<String> existLocationList, String code, String message) {
        hideProgressDialog();

        //검색결과가 없을때 제외하고 바꿔주고 바꼈다고 알려줌
        if (existLocationList.size() != 0) {
            mLocationList.clear();
            //아무것도없는값 보내줄때 예외처리
            if (existLocationList.get(0).equals("")){
                return;
            }
            mLocationList.addAll(existLocationList);
            mSearchLocationListAdaper.notifyDataSetChanged();
        }
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

    @Override
    public void saveHouseSuccess(int code, String message) {
        hideProgressDialog();
        if(code == 100){

        }
        showCustomToast(message);
    }

    @Override
    public void saveHouseFailure() {

    }

}
