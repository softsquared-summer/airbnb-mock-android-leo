package com.shinplest.airbnbclone.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;

import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity {

    private Button mBtnRegister;
    private EditText mEtPhonenum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtPhonenum = findViewById(R.id.et_login_phonenum);
        mEtPhonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String phonenum = mEtPhonenum.getText().toString();

                //핸드폰 유효성 검사
                if(!Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", phonenum))
                {
                    mBtnRegister.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_clickable));
                    mBtnRegister.setEnabled(true);
                }
                else{
                    mBtnRegister.setEnabled(false);
                }


            }
        });





        mBtnRegister = findViewById(R.id.btn_login_register_by_phone_number);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
