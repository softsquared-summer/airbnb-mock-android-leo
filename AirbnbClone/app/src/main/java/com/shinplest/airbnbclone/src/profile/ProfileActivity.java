package com.shinplest.airbnbclone.src.profile;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.profile.interfaces.ProfileActivityView;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {

    private ProfileResponse.Result mProfile = null;

    private SimpleDraweeView mSvProfilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getUiSourse();

        tryGetProfile();
    }

    private void getUiSourse() {
        mSvProfilePhoto = findViewById(R.id.sv_profile_profile_photo);
    }

    private void UpdateUI() {
        mSvProfilePhoto.setImageURI(Uri.parse(mProfile.getProfileImgUrl()));
    }

    void tryGetProfile() {
        final ProfileService profileService = new ProfileService(this);
        showProgressDialog();
        profileService.getProfile();
    }

    @Override
    public void validateSuccess(ProfileResponse profileResponse, int code, String message) {
        hideProgressDialog();
        //조회가 성공했을때,
        if (code == 100) {
            Log.d("uitest", "validateSuccess: ");
            mProfile = profileResponse.getResult();
            //프로필이 없지 않으면
            if (mProfile != null) {
                UpdateUI();
                Log.d("uitest", "tryGetProfile: 프로필 업데이트 성공");
            }
        }
    }

    @Override
    public void validateFailure(String message) {

    }
}
