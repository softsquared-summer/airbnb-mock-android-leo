package com.shinplest.airbnbclone.src.main;


//질문 리스트

/*
    프로필과 저장목록에서 모든게 같이 스크롤되게 만들고 싶음
    리사이클러뷰에 온클릭 리스너를 다는 효율적인 방법
    구글 로그인과 일반로그인 데이터를 분리해서 가져오는 효율적인 방법

 */

//안쓰는 import 자동 정리 ctrl + alt + o


//        android:usesCleartextTraffic="true"
//메니페스트 http 접속 허용해주려고 임시로 만듬

//주요 고안점

//splash화면에서 불필요한 시간을 없애기 위해서, drawable로 구현.
//로그인 액티비티가 필요할때만 나오기 위해서 엠티 액티비티로 분기를 해서 토큰유무에 따라 연결
//fremelayout 대신 viewpager로 구현
//glide보다 fresco가 로딩속도가 더 빠르고, restrofit과 호환성 좋아서 사용 게다가 자동으로 이미지 잘라주는게 맘에듬.
//커스텀 라이브러리로 snap효과 일정하게 주고 양쪽에 뷰 거리가 있는거 trick으로 해결


//요즘 핫한 파이어 베이스를 통한 구글 로그인 구현 -> 로그인 정보 가져와서 profile에 넣어주는 기능 구현 필요

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.main.fragments.FragMessage;
import com.shinplest.airbnbclone.src.main.fragments.FragProfile;
import com.shinplest.airbnbclone.src.main.fragment_savelist.FragSavelist;
import com.shinplest.airbnbclone.src.main.fragment_search.FragSearch;
import com.shinplest.airbnbclone.src.main.fragments.FragTravel;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        //기본으로 로드해주는 부분
        loadFragment(new FragSearch());

        //버텀 네비바에 따라 바뀌는 부분
        mBottomNavigationView = findViewById(R.id.bottom_navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.action_search:
                fragment = new FragSearch();
                break;
            case R.id.action_savelist:
                fragment = new FragSavelist();
                break;
            case R.id.action_travel:
                fragment = new FragTravel();
                break;
            case R.id.action_message:
                fragment = new FragMessage();
                break;
            case R.id.action_profile:
                fragment = new FragProfile();
                break;
        }
        return loadFragment(fragment);
    }
}
