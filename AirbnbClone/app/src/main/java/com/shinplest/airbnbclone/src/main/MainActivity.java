package com.shinplest.airbnbclone.src.main;

//주요 고안점

//splash화면에서 불필요한 시간을 없애기 위해서, drawable로 구현.




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
