package com.shinplest.airbnbclone.src.house;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.house.interfaces.HouseActivityView;
import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

import java.util.ArrayList;

public class HouseActivity extends BaseActivity implements HouseActivityView {

    private int mHouseNo;

    private HouseResponse.Result mHouseData;


    ///ui
    private SimpleDraweeView mSvHouseHostProfile;
    private TextView mTvHouseTypeFirst, mTvHouseTitle, mTvHouseLocation,
            mTvHouseHost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        getUiSourse();

        mHouseNo = getIntent().getIntExtra("houseNo", 1);
        tryGetHouseInfo();

    }

    private void updateUi() {
        //이미지
        ArrayList<HouseResponse.Image> houseDataImages = mHouseData.getImages();
        HouseResponse.Info houseDataInfo = mHouseData.getInfo();
        //mSvHouseHostProfile.setImageURI(Uri.parse(houseDataInfo.gethouse));
        mTvHouseTypeFirst.setText(houseDataInfo.getHouseType());
        mTvHouseTitle.setText(houseDataInfo.getHouseTitle());
        mTvHouseLocation.setText(houseDataInfo.getHouseLocation());
        mTvHouseHost.setText(houseDataInfo.getHouseHost());
    }

    private void getUiSourse() {
        mSvHouseHostProfile = findViewById(R.id.sv_house_host_profile);
        mTvHouseTypeFirst = findViewById(R.id.tv_house_type_first);
        mTvHouseTitle = findViewById(R.id.tv_houset_title);
        mTvHouseLocation = findViewById(R.id.tv_house_location);
        mTvHouseHost = findViewById(R.id.tv_house_host);
    }

    void tryGetHouseInfo() {
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        houseService.getHouseinfo(mHouseNo);
    }

    @Override
    public void getHouseSuccess(HouseResponse.Result houseResponseResult, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("하우스 데이터 가져오기 성공");
            mHouseData = houseResponseResult;
            updateUi();
        }
    }

    @Override
    public void getHouseFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");
    }
}
