package com.shinplest.airbnbclone.src.main.models;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GoogleUserInfo {
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public GoogleUserInfo(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
        user = mAuth.getCurrentUser();
    }

    public String getGoogleUserEmail(){
        return user.getEmail();
    }

    public String getGoogleUserName(){
        return user.getDisplayName();
    }

    public String getGoogleUserProfilePhotoUrl(){
        return user.getPhotoUrl().toString();
    }
}
