package com.shinplest.airbnbclone.src.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;


//기본적으로 jwt가 없는 상황을 가정 없을경우, 이메일과 password로 jwt를 받고, sharedpreference에 저장

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private RequestRegister userInfo;

    private EditText mEtEmail;
    private EditText mEtPassword;


    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //버튼 클릭시 jwt 토큰 요청후 sharedpreference에 저장

        mEtEmail = findViewById(R.id.et_login_email);
        mEtPassword = findViewById(R.id.et_login_password);

        mBtnLogin = findViewById(R.id.btn_login_login);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                userInfo = new RequestRegister(mEtEmail.getText().toString(), mEtPassword.getText().toString());
//                tryGetJwt();
            }
        });
    }

    private void tryGetJwt(){
        showProgressDialog();

        final LoginService loginService = new LoginService(this);
        loginService.getjwt();
    }

    @Override
    public void validateLoginSuccess(boolean isSuccess, int code, String message) {
        hideProgressDialog();
    }

    @Override
    public void validateLoginFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
