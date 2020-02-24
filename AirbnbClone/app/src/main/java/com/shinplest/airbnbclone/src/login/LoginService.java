package com.shinplest.airbnbclone.src.login;

import android.content.SharedPreferences;

import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.login.models.JwtResponse;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;
import static com.shinplest.airbnbclone.src.ApplicationClass.sSharedPreferences;

public class LoginService {

    private final LoginActivityView mLoginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    //이메일과 아이디 보내서 jwt를 받아옴
    void postJwt(RequestJwt requestJwt){
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postJwt(requestJwt).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                final JwtResponse jwtResponse = response.body();
                if (jwtResponse == null){
                    mLoginActivityView.validateLoginFailure(null);
                }
                //성공했을때만 저장
                if (jwtResponse.getCode() == 100){
                    mLoginActivityView.validateLoginSuccess(jwtResponse.getCode(), jwtResponse.getMessage());
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    editor.putString(X_ACCESS_TOKEN, jwtResponse.getResult().getJwt()).apply();
                }
                else
                    mLoginActivityView.validateLoginSuccess(jwtResponse.getCode(), jwtResponse.getMessage());
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                mLoginActivityView.validateLoginFailure(null);
            }
        });
    }


}
