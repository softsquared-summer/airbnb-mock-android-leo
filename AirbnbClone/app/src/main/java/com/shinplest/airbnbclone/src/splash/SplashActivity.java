package com.shinplest.airbnbclone.src.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.LoginActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityIntent;

        //테스트용으로 int값으로 하였음, 토큰있으면 메인, 없으면 로그인 창 jwt위해 남겨놓는다.
        int token = 10;
        if(token != 10){
            activityIntent = new Intent(this, MainActivity.class);
        }
        else
        {
            activityIntent = new Intent(this, LoginActivity.class);
        }
        startActivity(activityIntent);
        finish();
    }
}
