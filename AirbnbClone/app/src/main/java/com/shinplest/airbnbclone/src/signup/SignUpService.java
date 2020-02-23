package com.shinplest.airbnbclone.src.signup;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.signup.interfaces.SignUpActivityView;
import com.shinplest.airbnbclone.src.signup.interfaces.SignUpRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;

public class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    public SignUpService(SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }

    void getPhoneAvailable(String phoneNum){
        SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        Call<DefaultResponse> call = signUpRetrofitInterface.getPhoneAvailable(phoneNum);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                mSignUpActivityView.validateSignUpSuccess(defaultResponse.getMessage());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
