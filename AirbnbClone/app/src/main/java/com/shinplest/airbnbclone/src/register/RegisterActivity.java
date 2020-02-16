package com.shinplest.airbnbclone.src.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

public class RegisterActivity extends BaseActivity {
    private Button mBtnRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnRegister = findViewById(R.id.btn_register_test);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json 레트로핏으로 보내기
            }
        });
    }
}
