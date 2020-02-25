package com.shinplest.airbnbclone.src.profile_modify;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileModifyActivityView;

public class ProfileModifyActivity extends BaseActivity implements ProfileModifyActivityView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);
    }

    @Override
    public void validateJwtLoginSuccess(String message, int code) {
        hideProgressDialog();
        if(code == 100){

        }
    }

    @Override
    public void validateJwtLoginFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
