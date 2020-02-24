package com.shinplest.airbnbclone.src.login;

import android.util.Log;

import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.login.models.JwtResponse;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;
import com.shinplest.airbnbclone.src.login.models.UserNoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shinplest.airbnbclone.src.ApplicationClass.BASE_URL;
import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;
import static com.shinplest.airbnbclone.src.ApplicationClass.retrofit;

public class LoginService {

    private final LoginActivityView mLoginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postJwt(RequestJwt requestJwt){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final LoginRetrofitInterface loginRetrofitInterface = retrofit.create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postJwt(requestJwt).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(Call<JwtResponse> call, Response<JwtResponse> response) {
                final JwtResponse jwtResponse = response.body();
                if (jwtResponse == null){
                    mLoginActivityView.validateLoginFailure(null);
                }
                mLoginActivityView.validateLoginSuccess(jwtResponse.getResult().getJwt());
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                mLoginActivityView.validateLoginFailure(null);
            }
        });
    }

    void getUserNo(){
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.getUserNo().enqueue(new Callback<UserNoResponse>() {
            @Override
            public void onResponse(Call<UserNoResponse> call, Response<UserNoResponse> response) {
                UserNoResponse userNoResponse = response.body();
                Log.d("test", userNoResponse.getMessage());
                mLoginActivityView.validateJwtLoginSuccess();
            }

            @Override
            public void onFailure(Call<UserNoResponse> call, Throwable t) {

            }
        });
    }
}
