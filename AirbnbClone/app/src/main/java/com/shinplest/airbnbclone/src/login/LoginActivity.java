package com.shinplest.airbnbclone.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.ApplicationClass;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;
import com.shinplest.airbnbclone.src.main.MainActivity;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.shinplest.airbnbclone.src.ApplicationClass.sSharedPreferences;


//기본적으로 jwt가 없는 상황을 가정 없을경우, 이메일과 password로 jwt를 받고, sharedpreference에 저장

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private RequestJwt requestJwt;
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

        //버튼을 클릭했을때 jwt를 받아오고 로그인을 시켜줌.
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestJwt = new RequestJwt();
                requestJwt.setEmail(mEtEmail.getText().toString());
                requestJwt.setPw(mEtPassword.getText().toString());

                tryPostLogin();
                tryGetUserNo();
            }
        });
    }

    private void tryPostLogin(){
        final LoginService loginService = new LoginService(this);
        showProgressDialog();
        loginService.postJwt(requestJwt);
    }

    private void tryGetUserNo(){
        final LoginService loginService = new LoginService(this);
        showProgressDialog();
        loginService.getUserNo();
    }


    //jwt로그인 됐을때, sharedpreference에 저장하기
    @Override
    public void validateLoginSuccess(String jwt) {
        hideProgressDialog();
        showCustomToast(jwt);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, jwt).apply();
        Log.d("test", sSharedPreferences.getString(X_ACCESS_TOKEN, "nothing"));
    }

    @Override
    public void validateLoginFailure(String message) {
        hideProgressDialog();
        Log.d("test", "로그인 실패");
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }

    @Override
    public void validateJwtLoginSuccess() {
        hideProgressDialog();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateJwtLoginFailure() {

    }
}
