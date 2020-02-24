package com.shinplest.airbnbclone.src.signup;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

import static com.shinplest.airbnbclone.src.ApplicationClass.getNumbersFromString;


//단순히 전화번호 있는지 없는지를 검증하고, 있으면 Login Activity 없으면 Register Activity로 넘겨줌

public class SignUpActivity extends BaseActivity implements SignUpActivityView {

    //스피너 바꿔줌
    private Spinner mSpnCountryCode;
    private TextView mTvCountryCode;
    private ImageView mIvExit;



    //구글로그인 관련 변수
    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;

    private Button mBtnRegister;
    private EditText mEtPhonenum;

    private LinearLayout mLlLogin;

    private String phoneNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //전화번호 선택에 따라 국가 번호 바꿔줌
        changeCountryCode();

        //엑스버튼 앱자체 종료
        mIvExit = findViewById(R.id.iv_signup_exit);
        mIvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("앱을 종료합니다.");
                finishAffinity();
            }
        });

        //구글로그인
        signInButton = findViewById(R.id.signInButton);
        mAuth = FirebaseAuth.getInstance();

        //구글 로그인 객체
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //구글로그인 나중에 구현
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToast("현재 개발중인 기능입니다. 잠시만 기다려주세요");
                //signIn();
            }
        });

        //전화번호 검증하는 부분
        validatePhoneNumber();


        //버튼 누르면 폰번호 있는지 없는지 판단후
        //있으면 -> 로그인, 없으면 -> 가입 넘겨줌
        mBtnRegister = findViewById(R.id.btn_login_register_by_phone_number);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryGetPoneAvailable(phoneNum);
            }
        });


        //로그인누르면 로그인창으로 넘겨주는 부분
        mLlLogin = findViewById(R.id.ll_singup_login);
        mLlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validatePhoneNumber() {
        mEtPhonenum = findViewById(R.id.et_login_phonenum);
        mEtPhonenum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        mEtPhonenum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNum = mEtPhonenum.getText().toString();

                //핸드폰 유효성 검사 그에따라 색을 바꿔주고 클릭 가능 불가능여부 바꾸어줌
                if (Pattern.matches("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$", phoneNum)) {
                    mBtnRegister.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_clickable));
                    mBtnRegister.setEnabled(true);
                } else {
                    mBtnRegister.setBackground(getResources().getDrawable(R.drawable.shape_login_btn_unclickable));
                    mBtnRegister.setEnabled(false);
                }
            }
        });
    }

    private void changeCountryCode() {
        mSpnCountryCode = findViewById(R.id.spn_signup_country);
        mSpnCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTvCountryCode = findViewById(R.id.tv_signup_country_code);
                String countryCode = mSpnCountryCode.getSelectedItem().toString();
                String number = getNumbersFromString(countryCode);
                mTvCountryCode.setText("+"+number);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void tryGetPoneAvailable(String phoneNum) {
        final SignUpService signUpService = new SignUpService(this);
        showProgressDialog();
        signUpService.getPhoneAvailable(phoneNum);
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
        } else {
            showCustomToast("google login error code : no user");
        }
    }

    //핸드폰 번호가 있을 경우
    @Override
    public void validateSignUpSuccess(String message, int code) {
        hideProgressDialog();

        //가입된 번호가 있을경우
        if (code == 101) {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            showCustomToast("가입된 아이디가 존재합니다.\n로그인 하세요");
        }
        //번호가 없을 경우-> 회원가입
        else {
            Intent intent = new Intent(SignUpActivity.this, RegisterActivity.class);
            intent.putExtra("phoneNum", mEtPhonenum.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public void validateSignUpFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
