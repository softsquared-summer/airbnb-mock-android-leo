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

public class LoginActivity extends BaseActivity implements LoginActivityView {

    private ImageView mIvBackArrow;

    private EditText mEtEmail;
    private EditText mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //상단 버튼 누를시 뒤로가기
        mIvBackArrow = findViewById(R.id.iv_login_back_arrow);
        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mEtEmail = findViewById(R.id.et_login_email);
        mEtPassword = findViewById(R.id.et_login_password);
        mBtnLogin = findViewById(R.id.btn_login_login);
        //버튼을 클릭했을때 jwt를 받아오고 로그인을 시켜줌.
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 키보드 없애준다.
                hideKeyboard();

                RequestJwt requestJwt = new RequestJwt();
                //아이디 비밀번호 로그인시
                requestJwt.setEmail(mEtEmail.getText().toString().replace(" ", ""));
                requestJwt.setPw(mEtPassword.getText().toString().replace(" ", ""));
                tryPostLogin(requestJwt);
            }
        });
    }

    private void tryPostLogin(RequestJwt requestJwt) {
        final LoginService loginService = new LoginService(this);
        showProgressDialog();
        loginService.postJwt(requestJwt);
    }

    //jwt로그인 됐을때, sharedpreference에 저장하기
    //성공했으나 로그인 됐는지 실패했는지 알려줘야됨
    @Override
    public void validateLoginSuccess(int code, String message) {
        hideProgressDialog();
        //로그인 성공
        if (code == 100) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        //로그인 실패 -> 비밀번호 틀림
        else
            showCustomToast(message);
    }

    //네트워크문제로 성공 못했을 경우
    @Override
    public void validateLoginFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
