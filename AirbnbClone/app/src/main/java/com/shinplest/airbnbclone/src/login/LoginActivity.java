package com.shinplest.airbnbclone.src.login;

import android.content.Intent;
import android.os.Bundle;
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
                requestJwt.setEmail(mEtEmail.getText().toString().replace(" ", ""));
                requestJwt.setPw(mEtPassword.getText().toString().replace(" ", ""));
                tryPostLogin();
            }
        });
    }

    private void tryPostLogin() {
        final LoginService loginService = new LoginService(this);
        showProgressDialog();
        loginService.postJwt(requestJwt);
    }


    //jwt로그인 됐을때, sharedpreference에 저장하기
    //성공했으나 로그인 됐는지 실패했는지 알려줘야됨
    @Override
    public void validateLoginSuccess(int code) {
        hideProgressDialog();
        if (code == 100) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (code == 200)
            showCustomToast("아이디가 존재하지 않습니다.");
        else
            showCustomToast("비밀번호를 확인하세요.");
    }


    //네트워크문제로 성공 못했을 경우
    @Override
    public void validateLoginFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
