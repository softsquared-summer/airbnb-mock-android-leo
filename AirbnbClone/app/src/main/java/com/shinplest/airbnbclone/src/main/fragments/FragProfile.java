package com.shinplest.airbnbclone.src.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;

public class FragProfile extends BaseFragment {
    private FirebaseAuth mAuth;

    //view
    private SimpleDraweeView mSdProfilePhoto;
    private TextView mTvUserName;

    Button mBtnLogout;
    public FragProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //로그아웃 하기위해서 가져옴
        mAuth = FirebaseAuth.getInstance();

        //구글 아이디로 프로필 업데이트 해주는 부분
        FirebaseUser user = mAuth.getCurrentUser();
        mSdProfilePhoto = view.findViewById(R.id.sd_frag_profile_profile);
        mTvUserName = view.findViewById(R.id.tv_frag_profile_username);
        updateUI(user);


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
    private  void updateUI(FirebaseUser user){
        mSdProfilePhoto.setImageURI(user.getPhotoUrl());
        mTvUserName.setText(user.getDisplayName());
    }
}
