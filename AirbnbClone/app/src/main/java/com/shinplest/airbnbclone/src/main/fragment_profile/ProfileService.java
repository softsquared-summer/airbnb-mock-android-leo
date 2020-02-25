package com.shinplest.airbnbclone.src.main.fragment_profile;

import android.util.Log;

import com.shinplest.airbnbclone.src.main.fragment_profile.interfaces.ProfileFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_profile.interfaces.ProfileRetrofitInterface;
import com.shinplest.airbnbclone.src.main.fragment_profile.models.SimpleUserinfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.ApplicationClass.USER_NO;
import static com.shinplest.airbnbclone.src.ApplicationClass.getRetrofit;

public class ProfileService {
    private final ProfileFragmentView mProfileFragmentView;

    public ProfileService(ProfileFragmentView profileFragmentView) {
        this.mProfileFragmentView = profileFragmentView;
    }

    void getSimpleUserInfo() {
        ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);

        Call<SimpleUserinfoResponse> call = profileRetrofitInterface.getSimpleUserInfo(USER_NO);
        call.enqueue(new Callback<SimpleUserinfoResponse>() {
            @Override
            public void onResponse(Call<SimpleUserinfoResponse> call, Response<SimpleUserinfoResponse> response) {
                SimpleUserinfoResponse simpleUserinfoResponse = response.body();
                Log.d("network", "onResponse: firstname"+simpleUserinfoResponse.getResult().getFirstName());
                mProfileFragmentView.validateSuccess(simpleUserinfoResponse.getResult().getProfileImgUrl(),
                        simpleUserinfoResponse.getResult().getFirstName(), simpleUserinfoResponse.getCode(), simpleUserinfoResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SimpleUserinfoResponse> call, Throwable t) {

            }
        });
    }
}
