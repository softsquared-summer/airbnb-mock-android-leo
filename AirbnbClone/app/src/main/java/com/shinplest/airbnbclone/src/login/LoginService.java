package com.shinplest.airbnbclone.src.login;

import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.login.models.JwtResponse;
import com.shinplest.airbnbclone.src.login.models.RequestJwt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shinplest.airbnbclone.src.ApplicationClass.BASE_URL;
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
                JwtResponse jwtResponse = response.body();
                mLoginActivityView.validateLoginSuccess(jwtResponse.getResult().getJwt());
            }

            @Override
            public void onFailure(Call<JwtResponse> call, Throwable t) {
                mLoginActivityView.validateLoginFailure(null);
            }
        });
    }
}
