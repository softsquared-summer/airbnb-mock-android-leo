package com.shinplest.airbnbclone.src.main;

//주요 고안점

//splash화면에서 불필요한 시간을 없애기 위해서, drawable로 구현.
//로그인 액티비티가 필요할때만 나오기 위해서 엠티 액티비티로 분기를 해서 토큰유무에 따라 연결
//fremelayout 대신 viewpager로 구현


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

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
