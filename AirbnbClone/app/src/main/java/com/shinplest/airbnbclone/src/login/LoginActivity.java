package com.shinplest.airbnbclone.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shinplest.airbnbclone.src.ApplicationClass.BASE_URL;
import static com.shinplest.airbnbclone.src.ApplicationClass.retrofit;

public class LoginActivity extends BaseActivity {

    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //버튼 클릭시 jwt 토큰 요청후 sharedpreference에 저장

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();

            }
        });
    }
}
