package com.shinplest.airbnbclone.src.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterRetrofitInterface;
import com.shinplest.airbnbclone.src.register.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shinplest.airbnbclone.src.ApplicationClass.BASE_URL;

public class RegisterActivity extends BaseActivity {
    private Button mBtnRegister;
    private UserInfo userInfo;

    //모든 에딧텍스트
    private EditText mEtName;
    private EditText mEtLastName;
    private EditText mEtBirthday;
    private EditText mEtEmail;
    private EditText mEtPassword;

    //
    private String phoneNum;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnRegister = findViewById(R.id.btn_register_test);

        final String phoneNum = getIntent().getExtras().getString("phoneNum","010-0000-0000");
        showCustomToast(phoneNum);

        getEditText();

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json 레트로핏으로 보내기
                getEditTextInfo();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegisterRetrofitInterface registerRetrofitInterface = retrofit.create(RegisterRetrofitInterface.class);

                Call<UserInfo> call = registerRetrofitInterface.postTest(userInfo);
                call.enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                        showCustomToast(response.raw().toString());
                        showCustomToast("good");
                    }

                    @Override
                    public void onFailure(Call<UserInfo> call, Throwable t) {

                    }
                });
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
       // userInfo = new UserInfo(phoneNum, mEtName.getText().toString(), mEtLastName.getText().toString(), mEtBirthday.getText().toString(), mEtEmail.getText().toString(), mEtPassword.getText().toString());
    }
}
