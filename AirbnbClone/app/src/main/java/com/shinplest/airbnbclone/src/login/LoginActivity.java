package com.shinplest.airbnbclone.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.main.MainActivity;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;
import static com.shinplest.airbnbclone.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private UserInfo userInfo;

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

                userInfo = new UserInfo(mEtEmail.getText().toString(), mEtPassword.getText().toString());
                tryGetJwt();
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
