package com.shinplest.airbnbclone.src.main.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GoogleUserInfo {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public GoogleUserInfo(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
        if (mAuth.getCurrentUser() != null)
            user = mAuth.getCurrentUser();
    }

    public String getGoogleUserEmail() {
        return user.getEmail();
    }

    public String getGoogleUserName() {
        if (user.getDisplayName() != null)
            return user.getDisplayName();
        else
            return "";
    }

    public String getGoogleUserProfilePhotoUrl() {
        return user.getPhotoUrl().toString();
    }
}
