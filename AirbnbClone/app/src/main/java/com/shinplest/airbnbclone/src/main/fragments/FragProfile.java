package com.shinplest.airbnbclone.src.main.fragments;

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
import com.google.firebase.auth.FirebaseUser;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;
import com.shinplest.airbnbclone.src.main.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragProfile extends BaseFragment {
    private FirebaseAuth mAuth;

    //view
    private SimpleDraweeView mSdProfilePhoto;
    private TextView mTvUserName;

    private RecyclerView mRvSetting;

    private Button mBtnLogout;

    public FragProfile() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //프로필 리사이클러뷰
        mRvSetting = view.findViewById(R.id.rv_frag_profile_setting);
        mRvSetting.setLayoutManager(new LinearLayoutManager(getContext()));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Fruits"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Apple"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Orange"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Banana"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Cars"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Audi"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Aston Martin"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "BMW"));
        data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Cadillac"));

        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "Places");
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Kerala"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Tamil Nadu"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Karnataka"));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "Maharashtra"));

        data.add(places);

        mRvSetting.setAdapter(new ExpandableListAdapter(data));

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
