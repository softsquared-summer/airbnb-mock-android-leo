package com.shinplest.airbnbclone.src.register;

import android.content.Intent;

import com.shinplest.airbnbclone.src.main.MainActivity;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterActivityView;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterRetrofitInterface;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shinplest.airbnbclone.src.ApplicationClass.BASE_URL;
import static com.shinplest.airbnbclone.src.ApplicationClass.retrofit;

public class RegisterService {
    private final RegisterActivityView mRegisterActivityView;

    RegisterService(RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    void postRegister(RequestRegister requestRegister){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RegisterRetrofitInterface registerRetrofitInterface = retrofit.create(RegisterRetrofitInterface.class);

        Call<DefaultResponse> call = registerRetrofitInterface.postTest(requestRegister);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                mRegisterActivityView.validateSuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mRegisterActivityView.validateFailure();
            }
        });
    }
}
