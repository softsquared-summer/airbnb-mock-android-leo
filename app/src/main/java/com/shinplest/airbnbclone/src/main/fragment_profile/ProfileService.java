package com.shinplest.airbnbclone.src.main.fragment_profile;

import android.util.Log;

import com.shinplest.airbnbclone.src.main.fragment_profile.interfaces.ProfileFragmentView;
import com.shinplest.airbnbclone.src.profile.interfaces.ProfileRetrofitInterface;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class ProfileService {
    private final ProfileFragmentView mProfileFragmentView;

    public ProfileService(ProfileFragmentView profileFragmentView) {
        this.mProfileFragmentView = profileFragmentView;
    }

    void getSimpleUserInfo() {
        ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);

        Call<ProfileResponse> call = profileRetrofitInterface.getProfile(USER_NO);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse simpleUserinfoResponse = response.body();
                Log.d("network", "onResponse: firstname"+simpleUserinfoResponse.getResult().getFirstName());
                mProfileFragmentView.validateSuccess(simpleUserinfoResponse.getResult().getProfileImgUrl(),
                        simpleUserinfoResponse.getResult().getFirstName(), simpleUserinfoResponse.getCode(), simpleUserinfoResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }
}
