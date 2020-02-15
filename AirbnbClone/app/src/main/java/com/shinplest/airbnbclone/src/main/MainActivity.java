package com.shinplest.airbnbclone.src.main;

//주요 고안점

//splash화면에서 불필요한 시간을 없애기 위해서, drawable로 구현.
//로그인 액티비티가 필요할때만 나오기 위해서 엠티 액티비티로 분기를 해서 토큰유무에 따라 연결
//fremelayout 대신 viewpager로 구현




import android.os.Bundle;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
    }
}
