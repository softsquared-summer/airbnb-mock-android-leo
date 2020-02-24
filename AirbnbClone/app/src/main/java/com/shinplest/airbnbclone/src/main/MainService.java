package com.shinplest.airbnbclone.src.main;

import android.util.Log;

import com.shinplest.airbnbclone.src.login.interfaces.LoginRetrofitInterface;
import com.shinplest.airbnbclone.src.main.interfaces.MainActivityView;
import com.shinplest.airbnbclone.src.main.models.UserNoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityView mMainActivityView;

    public MainService(MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
    }


    //jwt보내서 회원정보 받아옴
    void getUserNo(){
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.getUserNo().enqueue(new Callback<UserNoResponse>() {
            @Override
            public void onResponse(Call<UserNoResponse> call, Response<UserNoResponse> response) {
                UserNoResponse userNoResponse = response.body();
                Log.d("test", userNoResponse.getMessage());
                mMainActivityView.validateJwtLoginSuccess(userNoResponse.getMessage(), userNoResponse.getResult().getUserNo());
            }

            @Override
            public void onFailure(Call<UserNoResponse> call, Throwable t) {

            }
        });
    }
}
