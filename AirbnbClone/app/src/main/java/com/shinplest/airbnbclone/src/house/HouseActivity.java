package com.shinplest.airbnbclone.src.house;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.house.interfaces.HouseActivityView;
import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.search.adapters.SearchHouseViewPageAdatper;

import java.util.ArrayList;

public class HouseActivity extends BaseActivity implements HouseActivityView {

    private int mHouseNo;
    private ViewPager mVpHouseImages;
    private HouseResponse.Result mHouseData;


    //테스트용
    String url[] = {"https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg"};

    ///ui
    private SimpleDraweeView mSvHouseHostProfile;
    private TextView mTvHouseTypeFirst, mTvHouseTitle, mTvHouseLocation,
            mTvHouseHost, mTvhouseDetail, mTvHouseMinimumStay, mTvHouseFacilty1,
            mTvHouseFacilty2, mTvHouseFacilty3, mTvHouseFacilty4, mTvHouseFacilty5,
            mTvHouseFacilty6, mTvHouseCheckIn, mTvHouseCheckOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        getUiSourse();


        mHouseNo = getIntent().getIntExtra("houseNo", 1);
        tryGetHouseInfo();

        mVpHouseImages.setAdapter(new SearchHouseViewPageAdatper(this, url));


    }

    private void updateUi() {
        //이미지
        ArrayList<HouseResponse.Image> houseDataImages = mHouseData.getImages();
        HouseResponse.Info houseDataInfo = mHouseData.getInfo();
        ArrayList<HouseResponse.Facility> houseDataFacilities= mHouseData.getFacilities();
        mSvHouseHostProfile.setImageURI(Uri.parse(houseDataInfo.getHostImage()));
        mTvHouseTypeFirst.setText(houseDataInfo.getHouseType());
        mTvHouseTitle.setText(houseDataInfo.getHouseTitle());
        mTvHouseLocation.setText(houseDataInfo.getHouseLocation());
        mTvHouseHost.setText(houseDataInfo.getHouseHost());
        mTvhouseDetail.setText(houseDataInfo.getHouseDetail());
        mTvHouseMinimumStay.setText(houseDataInfo.getMinimumStay());

        //편의시설 크기만큼 랜덤으로 부여 해줌 -> 편의시설 6개만 랜럼으로 추출해서 보여주기 위함
        int random = (int)(Math.random()*houseDataFacilities.size());
        //범위밖으로 나가면
        if(random+6 > houseDataFacilities.size()){
            random -= houseDataFacilities.size();
            random += 6;
        }
        mTvHouseFacilty1.setText(houseDataFacilities.get(random).getFacilitisename());
        mTvHouseFacilty2.setText(houseDataFacilities.get(random+1).getFacilitisename());
        mTvHouseFacilty3.setText(houseDataFacilities.get(random+2).getFacilitisename());
        mTvHouseFacilty4.setText(houseDataFacilities.get(random+3).getFacilitisename());
        mTvHouseFacilty5.setText(houseDataFacilities.get(random+4).getFacilitisename());
        mTvHouseFacilty6.setText(houseDataFacilities.get(random+5).getFacilitisename());

        mTvHouseCheckIn.setText(houseDataInfo.getCheckIn());
        mTvHouseCheckOut.setText(houseDataInfo.getCheckOut());

    }

    private void getUiSourse() {
        mSvHouseHostProfile = findViewById(R.id.sv_house_host_profile);
        mTvHouseTypeFirst = findViewById(R.id.tv_house_type_first);
        mTvHouseTitle = findViewById(R.id.tv_houset_title);
        mTvHouseLocation = findViewById(R.id.tv_house_location);
        mTvHouseHost = findViewById(R.id.tv_house_host);
        mVpHouseImages = findViewById(R.id.vp_house);
        mTvhouseDetail = findViewById(R.id.tv_house_house_detail);
        mTvHouseMinimumStay = findViewById(R.id.tv_house_house_minimum_stay);
        mTvHouseFacilty1 = findViewById(R.id.tv_house_facility_1);
        mTvHouseFacilty2 = findViewById(R.id.tv_house_facility_2);
        mTvHouseFacilty3 = findViewById(R.id.tv_house_facility_3);
        mTvHouseFacilty4 = findViewById(R.id.tv_house_facility_4);
        mTvHouseFacilty5 = findViewById(R.id.tv_house_facility_5);
        mTvHouseFacilty6 = findViewById(R.id.tv_house_facility_6);
        mTvHouseCheckIn = findViewById(R.id.tv_house_checkin_time);
        mTvHouseCheckOut = findViewById(R.id.tv_house_checkout_time);
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
