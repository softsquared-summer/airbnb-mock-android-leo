package com.shinplest.airbnbclone.src.profile_modify;

import android.util.Log;

import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileModifyActivityView;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileMotifyRetrofitInterface;
import com.shinplest.airbnbclone.src.profile_modify.models.RequestModifyProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class ProfileModifyService {
    private final ProfileModifyActivityView mProfileModifyActivityView;

    public ProfileModifyService(ProfileModifyActivityView profileModifyActivityView) {
        this.mProfileModifyActivityView = profileModifyActivityView;
    }

    void putModifiedProfile(RequestModifyProfile requestModifyProfile) {
        ProfileMotifyRetrofitInterface profileMotifyRetrofitInterface = getRetrofit().create(ProfileMotifyRetrofitInterface.class);

        Call<DefaultResponse> call = profileMotifyRetrofitInterface.putModifyProfile(USER_NO, requestModifyProfile);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse = response.body();
                Log.d("puttest", call.request().url().toString());
                mProfileModifyActivityView.validateProfileModifySuccess(defaultResponse.getMessage(), defaultResponse.getCode());
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                mProfileModifyActivityView.validateProfileModifyFailure(null);
            }
        });

    }
}
