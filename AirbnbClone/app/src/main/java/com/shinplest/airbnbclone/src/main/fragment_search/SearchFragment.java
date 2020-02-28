package com.shinplest.airbnbclone.src.main.fragment_search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.firebase.auth.FirebaseAuth;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseFragment;
import com.shinplest.airbnbclone.src.main.fragment_search.adapters.ContinueLookAroundAdapter;
import com.shinplest.airbnbclone.src.main.fragment_search.adapters.ExperienceAdapter;
import com.shinplest.airbnbclone.src.main.fragment_search.adapters.LookAroundAdapter;
import com.shinplest.airbnbclone.src.main.fragment_search.interfaces.SearchFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;
import com.shinplest.airbnbclone.src.main.models.GoogleUserInfo;
import com.shinplest.airbnbclone.src.search.SearchActivity;
import com.takusemba.multisnaprecyclerview.MultiSnapHelper;
import com.takusemba.multisnaprecyclerview.SnapGravity;
import com.yongbeom.aircalendar.AirCalendarDatePickerActivity;
import com.yongbeom.aircalendar.core.AirCalendarIntent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.GET_DATE;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.LOGIN_INFO;

public class SearchFragment extends BaseFragment implements SearchFragmentView {
    private FirebaseAuth mAuth;

    //view
    private LinearLayout mLlSearch;
    private Button mBtnDate;
    private TextView mTvLookAround;
    private RecyclerView mRvLookAround, mRvContinewLookAround, mRvExperience;
    private RecyclerView.LayoutManager mHorizontalLayoutManagerLookAround;
    private SnapHelper snapHelper;

    //세번째 데이터
    private ArrayList<SimpleExprerienceResponse.Result> mExperienceList;
    private ExperienceAdapter mExperienceAdapter;


    //첫번째 리사이클러뷰 데이터
    private String[] FirstReyclerTextSet = {"test", "숙소", "체험", "어드벤처", "test"};
    private String[] FirstRecyclerUrlSet = {"https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg",
            "https://www.koreahouse.or.kr/storage/contents/culture/2016/12/13/CONTENTS_CULTURE_KO_1481614839200",
            "data:image/jpeg;base64,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",
            "https://pds.joins.com/news/component/htmlphoto_mmdata/201906/05/htm_20190605181513963994.jpg"};

    //두번째 리사이클러 데이터
    String[] titleSet = {"test", "한국", "신주쿠, Shinjuku City, 일본", "맨해튼, 뉴욕, 뉴욕", "바르셀로나, 스페인", "로마", "바티칸", "test"};
    String[] contentSet = {"test", "숙소 및 체험", "숙소 및 체험", "숙소 및 체험", "숙소 및 체험", "숙소 및 체험", "숙소 및 체험", "test"};

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        //getUiSource
        mLlSearch = view.findViewById(R.id.ll_frag_search_search);
        mBtnDate = view.findViewById(R.id.btn_frag_search_date);
        mRvLookAround = view.findViewById(R.id.rv_frag_search_look_around);
        mRvContinewLookAround = view.findViewById(R.id.rv_frag_search_continue_look_around);
        mRvExperience = view.findViewById(R.id.rv_frag_search_experience);

        //--------구글 로그인

        mAuth = FirebaseAuth.getInstance();
        mTvLookAround = view.findViewById(R.id.tv_frag_search_look_around);
        //로그인 된 이름 따라서 UI 바꿔줌
        //구글로그인일 때만
        if (LOGIN_INFO.equals("google"))
            updateUI(mAuth);


        //-------------------------------------------

        //검색창 눌렀을때 온클릭 리스너
        mLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        //버튼 온클릭
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
        mRvLookAround.setHasFixedSize(true);
        mHorizontalLayoutManagerLookAround = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvLookAround.setLayoutManager(mHorizontalLayoutManagerLookAround);
        mRvLookAround.setAdapter(new LookAroundAdapter(FirstReyclerTextSet, FirstRecyclerUrlSet));
        snapHelper.attachToRecyclerView(mRvLookAround);


        //두번째 리사이클러
        mRvContinewLookAround.setHasFixedSize(true);
        mRvContinewLookAround.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRvContinewLookAround.setAdapter(new ContinueLookAroundAdapter(titleSet, contentSet));
        snapHelper.attachToRecyclerView(mRvContinewLookAround);

        //세번째 체험 리사이클러 뷰
        mRvExperience.setHasFixedSize(true);
        mRvExperience.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mExperienceList = new ArrayList<SimpleExprerienceResponse.Result>();
        mExperienceAdapter = new ExperienceAdapter(mExperienceList, getContext());
        mRvExperience.setAdapter(mExperienceAdapter);
        mRvExperience.addItemDecoration(new GridItemDecoration(10));


        //체험데이터 가져옴
        tryGetSimpleExperiencInfo();

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


    void tryGetSimpleExperiencInfo() {
        final SearchFragmentService searchFragmentService = new SearchFragmentService(this);
        showProgressDialog();
        searchFragmentService.getSimplerExperienceInfo();
    }

    @Override
    public void getExperiencessSuccess(ArrayList<SimpleExprerienceResponse.Result> simpleExprerienceResponseResultList, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            mExperienceList.addAll(simpleExprerienceResponseResultList);
            //데이터 가져오면 알려줌
            mExperienceAdapter.notifyDataSetChanged();
        }
        showCustomToastFrag("성공");

    }

    @Override
    public void getExperiencessFailure(String message) {

    }
}
