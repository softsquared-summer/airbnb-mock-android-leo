package com.shinplest.airbnbclone.src.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.LoginActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterActivityView;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;

public class RegisterActivity extends BaseActivity implements RegisterActivityView {
    private Button mBtnRegister;
    private RequestRegister requestRegister;

    //모든 에딧텍스트
    private EditText mEtFirstName;
    private EditText mEtLastName;
    private EditText mEtBirthday;
    private EditText mEtEmail;
    private EditText mEtPassword;

    private String phoneNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getUiSourse();
        //핸드폰 번호 인텐트 가져옴
        phoneNum = getIntent().getExtras().getString("phoneNum", "010-0000-0000");

        mBtnRegister = findViewById(R.id.btn_register_test);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EditText값 리퀘스트에 할당해줌
                requestRegister = new RequestRegister();
                setRequest();

                //로그인 요청 보냄
                tryPostRegiseter();
            }
        });
    }

    private void tryPostRegiseter() {
        hideKeyboard();
        final RegisterService registerService = new RegisterService(this);
        showProgressDialog();
        registerService.postRegister(requestRegister);
    }

    private void getUiSourse() {
        mEtLastName = findViewById(R.id.et_register_last_name);
        mEtFirstName = findViewById(R.id.et_register_first_name);
        mEtBirthday = findViewById(R.id.et_register_birthday);
        mEtEmail = findViewById(R.id.et_register_eamil);
        mEtPassword = findViewById(R.id.et_register_password);
    }

    private void setRequest() {
        requestRegister.setPhone(phoneNum);
        requestRegister.setLastName(mEtLastName.getText().toString());
        requestRegister.setFirstName(mEtFirstName.getText().toString());
        requestRegister.setBirthday(mEtBirthday.getText().toString());
        requestRegister.setEmail(mEtEmail.getText().toString());
        requestRegister.setPw(mEtPassword.getText().toString());
    }

    @Override
    public void validateSuccess(String text, int code) {
        hideProgressDialog();
        showCustomToast(text);
        if (code == 100) {
            showCustomToast("회원 가입이 성공했습니다.\n로그인 하세요.");
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void validateFailure() {
        hideProgressDialog();
        showCustomToast(getString(R.string.network_error));
    }
}
