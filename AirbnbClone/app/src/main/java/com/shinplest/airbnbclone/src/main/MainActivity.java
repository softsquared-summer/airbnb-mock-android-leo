package com.shinplest.airbnbclone.src.main;


//질문 리스트

/*
    리사이클러뷰에 온클릭 리스너를 다는 효율적인 방법 -> 인터넷에서 찾아본 방법들이 전부 뭔가 되지 않음, 쉽고 편하게 쓰는 법 어떤식으로 쓰는가?

    구글 로그인과 일반로그인 데이터를 분리해서 가져오는 효율적인 방법 -> 지금은 string 값을 intent로 전달해서 구분하고 있는데, 그렇게 하니까 좀 불편
    사용하는 액티비티마다 코드를 분기해서 짜는 건 조금 비효율 적인거 같은데 -> 일단 서버기준으로, 해야겠다.

    인터셉터 사용법(네트워크 코드 나누는 법) -> 자동으로 써져있다
    앱바 디자인 효과적으로 하는법


    jwt라는걸 sharedpreference에 저장하는게 자동로그인이 되게하려는거 아닌가? splash에서 jwt를 쏘면 되는거 아닌가? -> 어디에 저장해야하냐..?
*/

//메니페스트 http 접속 허용해주려고 임시로 만듬

//주요 고안점

//splash화면에서 불필요한 시간을 없애기 위해서, drawable로 구현. jwt 확인
//로그인 액티비티가 필요할때만 나오기 위해서 엠티 액티비티로 분기를 해서 토큰유무에 따라 연결
//구글 로그인과 일반로그인 모두 구현
//viewpager로 대신 framelayout으로 구현
//glide보다 fresco가 로딩속도가 더 빠르고, restrofit과 호환성 좋아서 사용 게다가 자동으로 이미지 잘라주는게 맘에듬.
//커스텀 라이브러리로 snap효과 일정하게 주고 양쪽에 뷰 거리가 있는거 trick으로 해결
//


//요즘 핫한 파이어 베이스를 통한 구글 로그인 구현 -> 로그인 정보 가져와서 profile에 넣어주는 기능 구현 필요

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.LoginService;
import com.shinplest.airbnbclone.src.main.fragment_message.MessageFragment;
import com.shinplest.airbnbclone.src.main.fragment_profile.ProfileFragment;
import com.shinplest.airbnbclone.src.main.fragment_savelist.SavelistFragment;
import com.shinplest.airbnbclone.src.main.fragment_search.SearchFragment;
import com.shinplest.airbnbclone.src.main.fragment_travel.TravelFragment;
import com.shinplest.airbnbclone.src.main.interfaces.MainActivityView;

import static com.shinplest.airbnbclone.src.ApplicationClass.LOGIN_INFO;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, MainActivityView {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        //기본으로 로드해주는 부분
        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            if (extras.getString("Login") == null)
                LOGIN_INFO = extras.getString("Login");
        }

        loadFragment(new SearchFragment());


        //버텀 네비바에 따라 바뀌는 부분
        mBottomNavigationView = findViewById(R.id.bottom_navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        tryGetUserNo();



    }

    private void tryGetUserNo(){
        final MainService loginService = new MainService(this);
        showProgressDialog();
        loginService.getUserNo();
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
                fragment = new SearchFragment();
                break;
            case R.id.action_savelist:
                fragment = new SavelistFragment();
                break;
            case R.id.action_travel:
                fragment = new TravelFragment();
                break;
            case R.id.action_message:
                fragment = new MessageFragment();
                break;
            case R.id.action_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void validateJwtLoginSuccess(String message, int userNo) {
        hideProgressDialog();
        showCustomToast("user no"+userNo);
    }

    @Override
    public void validateJwtLoginFailure(String message) {

    }
}
