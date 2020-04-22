package com.shinplest.airbnbclone.src.profile;

import com.shinplest.airbnbclone.src.profile.interfaces.ProfileActivityView;
import com.shinplest.airbnbclone.src.profile.interfaces.ProfileRetrofitInterface;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class ProfileService {
    private final ProfileActivityView mProfileActivityView;

    public ProfileService(ProfileActivityView profileActivityView) {
        this.mProfileActivityView = profileActivityView;
    }

    void getProfile(){
        ProfileRetrofitInterface profileRetrofitInterface = getRetrofit().create(ProfileRetrofitInterface.class);

        Call<ProfileResponse> call = profileRetrofitInterface.getProfile(USER_NO);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                ProfileResponse profileResponse = response.body();
                mProfileActivityView.validateSuccess(profileResponse, profileResponse.getCode(), profileResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });
    }
}
