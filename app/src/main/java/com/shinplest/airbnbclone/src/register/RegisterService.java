package com.shinplest.airbnbclone.src.register;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterActivityView;
import com.shinplest.airbnbclone.src.register.interfaces.RegisterRetrofitInterface;
import com.shinplest.airbnbclone.src.register.models.RequestRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class RegisterService {
    private final RegisterActivityView mRegisterActivityView;

    RegisterService(RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    void postRegister(RequestRegister requestRegister) {
        RegisterRetrofitInterface registerRetrofitInterface = getRetrofit().create(RegisterRetrofitInterface.class);

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
