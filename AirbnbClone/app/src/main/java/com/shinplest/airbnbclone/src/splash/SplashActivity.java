package com.shinplest.airbnbclone.src.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.signup.SignUpActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;

public class SplashActivity extends BaseActivity {
    private FirebaseAuth mAuth = null;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //프레스코는 한번만 이니시 되야 메모리 리크 막는다고 안내하더라.
        Fresco.initialize(this);

        Intent activityIntent;
        // 여기서 해야할 것 .

        //구글 로그인 오케이 || 토큰 있으면 메인으로
        //아니라면 로그인 액티비티로.
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        //구글 로그인이 되어 있거나 토큰이 있으면
        if (user != null) {
            //구글로그인이 되어있으면
            activityIntent = new Intent(this, MainActivity.class);
            activityIntent.putExtra("Login", "google");
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
