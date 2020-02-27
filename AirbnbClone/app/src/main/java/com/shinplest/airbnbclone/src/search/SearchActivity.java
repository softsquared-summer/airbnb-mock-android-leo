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
import com.shinplest.airbnbclone.src.search.adapters.SearchHouseViewPageAdatper;
import com.shinplest.airbnbclone.src.search.adapters.SearchHousesAdapter;
import com.shinplest.airbnbclone.src.search.adapters.SearchLocationListAdaper;
import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.ArrayList;

public class SearchActivity extends BaseActivity implements SearchActivityView {

    //뷰페이져 테스트
    ViewPager mViewPager;

    SearchHouseViewPageAdatper searchHouseViewPageAdatper;

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
    private SearchHousesAdapter mSearchHouseAdapter;
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

            //텍스트가 변할때 마다 검색리스트를 불러옴
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchWord = s.toString();
                tryGetExistLocation(searchWord);
                mSearchHouseAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

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


        //숙소 리스트뷰
        mHouseDataList = new ArrayList<>();
        mRvHouses.setHasFixedSize(true);
        mRvHouses.setLayoutManager(new LinearLayoutManager(this));
        mSearchHouseAdapter = new SearchHousesAdapter(this, mHouseDataList, this);
        mRvHouses.setAdapter(mSearchHouseAdapter);
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
        mRvHouses = findViewById(R.id.rv_search_houses);
    }


    private void tryGetExistLocation(String searchWord) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.getExistLocationList(searchWord);
    }


    private void tryGetSimpleHouseInfo(String searchWord) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.getSimpleHouseInfo(searchWord);
        Log.d("network", "tryGetSimpleHouseInfo: getsimplehouseinfo");

    }

    //search activity에서 저장해주는 함수

    @Override
    public void tryPostSaveHouse(int userNo, int houseNo) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.postSaveHouse(userNo, houseNo);
    }

    @Override
    public void tryDeleteSavedHouse(int userNo, int houseNo) {
        final SearchService searchService = new SearchService(this);
        showProgressDialog();
        searchService.deleteSavedHouse(userNo, houseNo);
    }


    //로케이션을 받아오면 로케이션 arraylist를 저장
    @Override
    public void searchSuccess(ArrayList<String> existLocationList, String code, String message) {
        hideProgressDialog();

        //검색결과가 없을때 제외하고 바꿔주고 바꼈다고 알려줌
        if (existLocationList.size() != 0) {
            mLocationList.clear();
            //아무것도없는값 보내줄때 예외처리
            if (existLocationList.get(0).equals("")) {
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
        mHouseDataList.clear();
        mHouseDataList.addAll(simpleHouseInfoResponse.getResult());
        mSearchHouseAdapter.notifyDataSetChanged();
    }

    @Override
    public void searchHouseFailure(String message) {
        hideProgressDialog();
    }

    @Override
    public void saveHouseSuccess(int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("저장목록에 저장되었습니다");
        }
        //showCustomToast(message);
    }

    @Override
    public void saveHouseFailure() {

    }

    @Override
    public void deleteSavedHouseSuccess(int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("저장목록에서 삭제 되었습니다.");
        }
    }

    @Override
    public void deleteSavedHouseFailure() {

    }

}
