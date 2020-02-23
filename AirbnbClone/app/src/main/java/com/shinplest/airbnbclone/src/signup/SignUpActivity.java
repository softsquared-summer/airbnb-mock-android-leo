package com.shinplest.airbnbclone.src.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.login.LoginActivity;
import com.shinplest.airbnbclone.src.main.MainActivity;
import com.shinplest.airbnbclone.src.register.RegisterActivity;
import com.shinplest.airbnbclone.src.signup.interfaces.SignUpActivityView;

import java.util.regex.Pattern;

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    //구글로그인 관련 변수
    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;

    private Button mBtnRegister;
    private EditText mEtPhonenum;

    private LinearLayout mLlLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //구글로그인
        signInButton = findViewById(R.id.signInButton);
        mAuth = FirebaseAuth.getInstance();

        //구글 로인 객체
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        //전화번호 검증하는 부분
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

                //핸드폰 유효성 검사 그에따라 색을 바꿔주고 클릭 가능 불가능여부 바꾸어주고 phone중간에 -삽입해줌
                if (Pattern.matches("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", phonenum)) {
                    mBtnRegister.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_clickable));
                    mBtnRegister.setEnabled(true);
                } else {
                    mBtnRegister.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_unclickable));
                    mBtnRegister.setEnabled(false);
                }
            }
        });

        //버튼 누르면 번호 가입 인텐트로 넘겨줌
        mBtnRegister = findViewById(R.id.btn_login_register_by_phone_number);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
                intent.putExtra("phoneNum", mEtPhonenum.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        mLlLogin = findViewById(R.id.ll_singup_login);
        mLlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            }
            //구글 로그인 실패할경우 뷰를 어떻게 업데이트 할 것인가.
            catch (ApiException e) {
                Log.w("e/", "Google sign in failed", e);
            }
        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            showCustomToast("Authentication Successed");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            showCustomToast("Authentication Failed.");
                            updateUI(null);
                        }
                    }
                });
    }

    //user가 있을경우에만
    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            showCustomToast("google login error code : no user");
        }
    }

    @Override
    public void validateSignUpSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
