package com.shinplest.airbnbclone.src.login;

import com.shinplest.airbnbclone.src.login.interfaces.LoginActivityView;
import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;

public class LoginService {

    private final LoginActivityView mLoginActivityView;

    public LoginService(LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void getjwt(){
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.getJwt().enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mLoginActivityView.validateLoginFailure(null);
            }
        });
    }
}
