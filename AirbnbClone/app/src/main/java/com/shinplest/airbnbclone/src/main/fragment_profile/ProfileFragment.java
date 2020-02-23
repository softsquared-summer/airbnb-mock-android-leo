package com.shinplest.airbnbclone.src.main.fragment_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.auth.FirebaseAuth;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;
import com.shinplest.airbnbclone.src.main.models.GoogleUserInfo;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends BaseFragment {
    private FirebaseAuth mAuth;

    //view
    private SimpleDraweeView mSdProfilePhoto;
    private TextView mTvUserName;

    private RecyclerView mRvSetting;
    private ExpandableListAdapter adapter;

    private Button mBtnLogout;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //프로필 리사이클러뷰
        mRvSetting = view.findViewById(R.id.rv_frag_profile_setting);
        mRvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "계정 관리"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "개인 정보", getResources().getDrawable(R.drawable.profile_userinfo)));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "결제 및 대금 수령",getResources().getDrawable(R.drawable.profile_payment)));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "알림",getResources().getDrawable(R.drawable.profile_noti)));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "호스팅"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "숙소 등록하기"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "체험 호스팅하기"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "지원"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "안전 센터"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "지역 지원 서비스에 연락하기"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "도움말"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "의견 남기기"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "약관"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "서비스 약관"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "로그 아웃"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "계정 전환하기"));

        mRvSetting.setAdapter(new ExpandableListAdapter(data));

        //로그아웃 하기위해서 가져옴
        mAuth = FirebaseAuth.getInstance();

        //구글 아이디로 프로필 업데이트 해주는 부분

        mSdProfilePhoto = view.findViewById(R.id.sd_frag_profile_profile);
        mTvUserName = view.findViewById(R.id.tv_frag_profile_username);
        updateUI(mAuth);


        mBtnLogout = view.findViewById(R.id.btn_frag_profile_logout);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃하고 앱 자체 종료
                signOut();
                showCustomToastFrag("로그아웃 되었습니다. ");
                getActivity().finishAffinity();
            }
        });
        return view;
    }

    //구글 로그아웃
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
    //구글 회원탈퇴
    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
    }

    //프로필 update
    private  void updateUI(FirebaseAuth auth){
        GoogleUserInfo user = new GoogleUserInfo(auth);
        mSdProfilePhoto.setImageURI(user.getGoogleUserProfilePhotoUrl());
        mTvUserName.setText(user.getGoogleUserName());
    }
}
