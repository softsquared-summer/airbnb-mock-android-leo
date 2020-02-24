package com.shinplest.airbnbclone.src.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;
import com.shinplest.airbnbclone.src.main.MainActivity;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.shinplest.airbnbclone.src.ApplicationClass.sSharedPreferences;


//기본적으로 jwt가 없는 상황을 가정 없을경우, 이메일과 password로 jwt를 받고, sharedpreference에 저장

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private ImageView mIvBackArrow;

    private RequestJwt requestJwt;
    private EditText mEtEmail;
    private EditText mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIvBackArrow = findViewById(R.id.iv_login_back_arrow);
        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
            }
        });
    }

    private void tryPostLogin(){
        final LoginService loginService = new LoginService(this);
        showProgressDialog();
        loginService.postJwt(requestJwt);
    }



    //jwt로그인 됐을때, sharedpreference에 저장하기
    @Override
    public void validateLoginSuccess(String jwt, int code) {
        hideProgressDialog();
        showCustomToast(jwt);
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putString(X_ACCESS_TOKEN, jwt).apply();
        //여기서 토큰 제대로 저장 되는 거 확인 로그아웃 시에, 토큰을 삭제해주면 된다.
        Log.d("token", sSharedPreferences.getString(X_ACCESS_TOKEN, "token save fail"));
        //jwt저장하고, 메인액티비티로 넘어감
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void validateLoginFailure(String message) {
        hideProgressDialog();
        Log.d("test", "로그인 실패");
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
