package com.shinplest.airbnbclone.src.main.fragment_search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.auth.FirebaseAuth;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;
import com.shinplest.airbnbclone.src.search.SearchActivity;
import com.shinplest.airbnbclone.src.main.models.GoogleUserInfo;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;
import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static com.shinplest.airbnbclone.src.ApplicationClass.GET_DATE;
import static com.shinplest.airbnbclone.src.ApplicationClass.LOGIN_INFO;

public class FragSearch extends BaseFragment {

    private FirebaseAuth mAuth;

    //view
    private LinearLayout mLlSearch;
    private Button mBtnDate;
    private Button mBtnAttendance;

    private TextView mTvLookAround;

    //first recycler view
    private RecyclerView mRvLookAround;
    private RecyclerView.Adapter mLookAroundAdapter;
    private RecyclerView.LayoutManager mHorizontalLayoutManagerLookAround;

    //secont recycler view
    private RecyclerView mRvContinewLookAround;
    private RecyclerView.Adapter mContinueLookAroundAdapter;
    private RecyclerView.LayoutManager mHorizontalLayoutManagerContinueLookAround;

    private SnapHelper snapHelper;

    public FragSearch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mAuth = FirebaseAuth.getInstance();
        mTvLookAround = view.findViewById(R.id.tv_frag_search_look_around);
        //로그인 된 이름 따라서 UI 바꿔줌
        //구글로그인일 때만
        if (LOGIN_INFO.equals("google"))
            updateUI(mAuth);

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
        mHorizontalLayoutManagerLookAround = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvLookAround.setLayoutManager(mHorizontalLayoutManagerLookAround);
        String[] textSet = {"test", "숙소", "체험", "어드벤처", "test"};
        int[] imgSet = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,};
        mRvLookAround.setAdapter(new LookAroundAdapter(textSet, imgSet));
        snapHelper.attachToRecyclerView(mRvLookAround);


        //두번째 리사이클러
        mRvContinewLookAround = view.findViewById(R.id.rv_frag_search_continue_look_around);
        mRvContinewLookAround.setHasFixedSize(true);
        mHorizontalLayoutManagerContinueLookAround = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvContinewLookAround.setLayoutManager(mHorizontalLayoutManagerContinueLookAround);
        String[] titleSet = {"test", "한국", "신주쿠, Shinjuku City, 일본", "맨해튼, 뉴욕, 뉴욕", "test"};
        String[] contentSet = {"test", "숙소 및 체험", "숙소 및 체험", "숙소 및 체험", "test"};
        mContinueLookAroundAdapter = new ContinueLookAroundAdapter(titleSet, contentSet);
        mRvContinewLookAround.setAdapter(mContinueLookAroundAdapter);
        snapHelper.attachToRecyclerView(mRvContinewLookAround);


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

    private void updateUI(FirebaseAuth auth) {
        GoogleUserInfo user = new GoogleUserInfo(auth);

        if (user != null)
            mTvLookAround.setText(user.getGoogleUserName() + "님, 무엇을 찾고 계신가요?");
    }


}
