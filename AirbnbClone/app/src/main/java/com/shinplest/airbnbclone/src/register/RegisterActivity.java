package com.shinplest.airbnbclone.src.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;

public class RegisterActivity extends BaseActivity {
    private Button mBtnRegister;
    private UserInfo userInfo;

    //모든 에딧텍스트
    private EditText mEtName;
    private EditText mEtLastName;
    private EditText mEtBirthday;
    private EditText mEtEmail;
    private EditText mEtPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnRegister = findViewById(R.id.btn_register_test);

        getEditText();

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json 레트로핏으로 보내기
                getEditTextInfo();
                showCustomToast(userInfo.getBirthday());
            }
        });
    }

    private void getEditText(){
        mEtName = findViewById(R.id.et_register_name);
        mEtLastName = findViewById(R.id.et_register_last_name);
        mEtBirthday = findViewById(R.id.et_register_birthday);
        mEtEmail = findViewById(R.id.et_register_eamil);
        mEtPassword = findViewById(R.id.et_register_password);
    }

    private void getEditTextInfo(){
        userInfo = new UserInfo(mEtName.getText().toString(), mEtLastName.getText().toString(), mEtBirthday.getText().toString(), mEtEmail.getText().toString(), mEtPassword.getText().toString());
    }
}
