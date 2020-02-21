package com.shinplest.airbnbclone.src.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.signup.SignUpActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;

public class SplashActivity extends BaseActivity {
    private FirebaseAuth mAuth = null;
    FirebaseUser user;
    private String jwtToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent activityIntent;
        // 여기서 해야할 것 .

        //구글 로그인 오케이 || 토큰 있으면 메인으로
        //아니라면 로그인 액티비티로.
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //구글 로그인이 되어 있거나 토큰이 있으면
        if (user != null) {
            activityIntent = new Intent(this, MainActivity.class);
        } else if (!X_ACCESS_TOKEN.equals("X-ACCESS-TOKEN")) {
            activityIntent = new Intent(this, MainActivity.class);
        }
        //토큰이 없으면
        else {
            activityIntent = new Intent(this, SignUpActivity.class);
        }
        startActivity(activityIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
