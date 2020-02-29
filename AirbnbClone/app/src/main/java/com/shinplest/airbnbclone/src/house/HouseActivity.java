package com.shinplest.airbnbclone.src.house;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.house.interfaces.HouseActivityView;
import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.house.models.RequestReserve;
import com.shinplest.airbnbclone.src.housereview.HouseReviewActivty;
import com.shinplest.airbnbclone.src.search.adapters.SearchHouseViewPageAdatper;
import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.GET_DATE;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;

public class HouseActivity extends BaseActivity implements HouseActivityView {

    private int mHouseNo, mIsSave;
    private ViewPager mVpHouseImages;
    private HouseResponse.Result mHouseData;
    private String[] houseDataImages;
    ///ui
    private SimpleDraweeView mSvHouseHostProfile;
    private TextView mTvHouseTypeFirst, mTvHouseTitle, mTvHouseLocation,
            mTvHouseHost, mTvhouseDetail, mTvHouseMinimumStay, mTvHouseFacilty1,
            mTvHouseFacilty2, mTvHouseFacilty3, mTvHouseFacilty4, mTvHouseFacilty5,
            mTvHouseFacilty6, mTvHouseCheckIn, mTvHouseCheckOut;
    private ImageView mIvIsSave, mIvBackArrow;
    private LinearLayout mLlMoreHouseReview;
    private Button mBtnReserve;

    private ArrayList<String> mArrayUnavailable;
    private RequestReserve mRequestReserve;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_TransparentTheme);
        setContentView(R.layout.activity_house);
        getUiSourse();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mHouseNo = extras.getInt("houseNo");
        mIsSave = extras.getInt("isSave");
        tryGetHouseInfo();
        //항상 예약가능한 날짜 가져와줌
        tryGetHouseReservationDate();

        //onclidk
        mIvIsSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //저장안되어있으면 색 바꿔주고 요청 바꾼다
                if (mIsSave == 0) {
                    mIvIsSave.setImageResource(R.drawable.house_saved);
                    tryPostSaveHouse(USER_NO, mHouseNo);
                } else {
                    mIsSave = 0;
                    mIvIsSave.setImageResource(R.drawable.house_save);
                    tryDeleteHouse(USER_NO, mHouseNo);
                }

            }
        });

        mLlMoreHouseReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HouseActivity.this, HouseReviewActivty.class);
                intent.putExtra("houseNo", mHouseNo);
                startActivity(intent);
            }
        });

        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAriCalendar();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GET_DATE) {
            if (data != null) {
                String startDate = data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_START_DATE);
                String endDate = data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_END_DATE);
                showCustomToast("Select Date range : \n" +  startDate+ "~" + endDate);
                RequestReserve requestReserve = new RequestReserve();
                requestReserve.setUserNo(USER_NO);
                requestReserve.setCheckIn(startDate);
                requestReserve.setCheckOut(endDate);
                requestReserve.setGuestCnt(2);
                requestReserve.setTotalPrice(40000);
                tryPostReserve(mHouseNo,requestReserve);
            }
        }
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
        mIvIsSave = findViewById(R.id.iv_house_save);
        mLlMoreHouseReview = findViewById(R.id.ll_house_more_house_review);
        mIvBackArrow = findViewById(R.id.iv_house_back);
        mBtnReserve = findViewById(R.id.btn_house_reserve);
    }

    private void updateUi() {
        //저장여부
        if (mIsSave == 0) {
            mIvIsSave.setImageResource(R.drawable.house_save);
        } else
            mIvIsSave.setImageResource(R.drawable.house_saved);
        //이미지
        houseDataImages = mHouseData.getImage().getImageUrl().split(",");
        mVpHouseImages.setAdapter(new SearchHouseViewPageAdatper(this, houseDataImages));


        HouseResponse.Info houseDataInfo = mHouseData.getInfo();
        ArrayList<HouseResponse.Facility> houseDataFacilities = mHouseData.getFacilities();
        mSvHouseHostProfile.setImageURI(Uri.parse(houseDataInfo.getHostImage()));
        mTvHouseTypeFirst.setText(houseDataInfo.getHouseType());
        mTvHouseTitle.setText(houseDataInfo.getHouseTitle());
        mTvHouseLocation.setText(houseDataInfo.getHouseLocation());
        mTvHouseHost.setText(houseDataInfo.getHouseHost());
        mTvhouseDetail.setText(houseDataInfo.getHouseDetail());
        mTvHouseMinimumStay.setText(houseDataInfo.getMinimumStay());

        //편의시설 크기만큼 랜덤으로 부여 해줌 -> 편의시설 6개만 랜럼으로 추출해서 보여주기 위함
        int random = (int) (Math.random() * houseDataFacilities.size());
        //범위밖으로 나가면
        if (random + 6 > houseDataFacilities.size()) {
            random -= houseDataFacilities.size();
            random += 6;
        }
        mTvHouseFacilty1.setText(houseDataFacilities.get(random).getFacilitisename());
        mTvHouseFacilty2.setText(houseDataFacilities.get(random + 1).getFacilitisename());
        mTvHouseFacilty3.setText(houseDataFacilities.get(random + 2).getFacilitisename());
        mTvHouseFacilty4.setText(houseDataFacilities.get(random + 3).getFacilitisename());
        mTvHouseFacilty5.setText(houseDataFacilities.get(random + 4).getFacilitisename());
        mTvHouseFacilty6.setText(houseDataFacilities.get(random + 5).getFacilitisename());

        mTvHouseCheckIn.setText(houseDataInfo.getCheckIn());
        mTvHouseCheckOut.setText(houseDataInfo.getCheckOut());


    }

    private void makeAriCalendar() {
        AirCalendarIntent intent = new AirCalendarIntent(this);
        intent.setSelectButtonText("결과 보기"); //the select button text
        intent.setResetBtnText("삭제"); //the reset button text
        intent.setWeekStart(Calendar.MONDAY);
        intent.setWeekDaysLanguage(AirCalendarIntent.Language.KO); //language for the weekdays
        intent.setBookingDateArray(mArrayUnavailable);
        intent.isBooking(true);
        startActivityForResult(intent, GET_DATE);
    }


    void tryGetHouseInfo() {
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        houseService.getHouseinfo(mHouseNo);
    }

    void tryPostSaveHouse(int userNo, int houseNo) {
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        ;
        houseService.postSaveHouse(userNo, houseNo);
    }

    void tryDeleteHouse(int userNo, int houseNo) {
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        ;
        houseService.deleteSavedHouse(userNo, houseNo);
    }

    void tryGetHouseReservationDate() {
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        houseService.getHouseReservationDate(mHouseNo);
    }

    void tryPostReserve(int houseNo, RequestReserve requestReserve){
        final HouseService houseService = new HouseService(this);
        showProgressDialog();
        houseService.postHouseReserve(houseNo, requestReserve);
    }
    @Override
    public void getHouseSuccess(HouseResponse.Result houseResponseResult, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            mHouseData = houseResponseResult;
            updateUi();
        }
    }

    @Override
    public void getHouseFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");
    }

    @Override
    public void saveHouseSuccess(int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("저장 목록에 저장되었습니다.");
        }
    }

    @Override
    public void saveHouseFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");

    }

    @Override
    public void deleteHouseSuccess(int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast("저장 목록에서 삭제되었습니다.");
        }
    }

    @Override
    public void deleteHouseFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");
    }

    //예약 불가능 날짜 가져오기
    @Override
    public void getReservationDateSuccess(String result, int code, String message) {
        if (code == 100) {
            String[] unAvailavbleDateList = result.split(",");
            Log.d("hello", "getReservationDateSuccess: " + unAvailavbleDateList[0]);
            ArrayList<String> unAvailavbleDateArray = new ArrayList<>(Arrays.asList(unAvailavbleDateList));
            mArrayUnavailable = unAvailavbleDateArray;
        }
    }

    @Override
    public void getReservationDateFailure(String message) {
        Log.d("testest", "getReservationDateSuccess: 실패");

        hideProgressDialog();
        showCustomToast("실패");
    }

    @Override
    public void postReserveSuccess(int code, String message) {
        hideProgressDialog();
        showCustomToast(message);
    }

    @Override
    public void postReserveFailure(String message) {
        Log.d("testest", "getReservationDateSuccess: 실패");
        hideProgressDialog();
        showCustomToast("실패");
    }


}
