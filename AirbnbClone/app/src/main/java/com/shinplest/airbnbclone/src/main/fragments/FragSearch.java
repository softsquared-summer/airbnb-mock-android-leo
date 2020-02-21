package com.shinplest.airbnbclone.src.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;
import com.shinplest.airbnbclone.src.Search.SearchActivity;
import com.shinplest.airbnbclone.src.main.CardAdapter;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;
import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static com.shinplest.airbnbclone.src.ApplicationClass.GET_DATE;

public class FragSearch extends BaseFragment {

    //view
    private LinearLayout mLlSearch;
    private Button mBtnDate;
    private Button mBtnAttendance;

    //first recycler view
    private RecyclerView mRvLookAround;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SnapHelper snapHelper;

    public FragSearch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//      뷰가 초기화 되기 전에 fresco initiate
        Fresco.initialize(getActivity());
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        //검색창 눌렀을때 온클릭 리스너
        mLlSearch = view.findViewById(R.id.ll_frag_search_search);
        mLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        //버튼 온클릭

        mBtnDate = view.findViewById(R.id.btn_frag_search_date);

        mBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToastFrag("show calender");
                AirCalendarIntent intent = new AirCalendarIntent(getActivity());
                intent.setSelectButtonText("결과 보기"); //the select button text
                intent.setResetBtnText("삭제"); //the reset button text
                intent.setWeekStart(Calendar.MONDAY);
                intent.setWeekDaysLanguage(AirCalendarIntent.Language.KO); //language for the weekdays
                startActivityForResult(intent, GET_DATE);
            }
        });


        //모든 리사이클러에서 쓰일 스냅헬퍼
        snapHelper = new MultiSnapHelper(SnapGravity.START, 1, 200);

        //첫번째 리사이클러
        mRvLookAround = view.findViewById(R.id.rv_frag_search_look_around);
        // 리사이클러뷰의 notify()처럼 데이터가 변했을 때 성능을 높일 때 사용한다.
        mRvLookAround.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvLookAround.setLayoutManager(layoutManager);
        String[] textSet = {"test", "숙소", "체험", "어드벤처", "test"};
        int[] imgSet = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,};
        adapter = new CardAdapter(textSet, imgSet);
        mRvLookAround.setAdapter(adapter);
        snapHelper.attachToRecyclerView(mRvLookAround);


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GET_DATE) {
            if (data != null) {
                showCustomToastFrag("Select Date range : \n" + data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_START_DATE) + "~" + data.getStringExtra(AirCalendarDatePickerActivity.RESULT_SELECT_END_DATE));
            }
        }
    }


}
