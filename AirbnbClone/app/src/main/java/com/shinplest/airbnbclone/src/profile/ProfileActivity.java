package com.shinplest.airbnbclone.src.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.profile.interfaces.ProfileActivityView;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;
import com.shinplest.airbnbclone.src.profile_modify.ProfileModifyActivity;

public class ProfileActivity extends BaseActivity implements ProfileActivityView {

    private ProfileResponse.Result mProfile = null;

    private SimpleDraweeView mSvProfilePhoto;
    private TextView mTvProfileTitle, mTvProfileRegisterDate, mTvProfileLccation,
            mTvProfileLaguage, mTvProfileJob, mTvProfileProvidedInfo;
    private ImageView mIvBackArrow, mIvModifyProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_profile);
        //가장먼저 ui 가져옴
        getUiSourse();

        mIvBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //프로필 수정으로 넘어감
        mIvModifyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileModifyActivity.class);
                intent.putExtra("Profile", mProfile);
                startActivity(intent);
            }
        });

    }

    //api호출은 시작될때 마다 해주도록
    @Override
    protected void onStart() {
        super.onStart();
        tryGetProfile();
    }

    private void getUiSourse() {
        mSvProfilePhoto = findViewById(R.id.sv_profile_profile_photo);
        mTvProfileTitle = findViewById(R.id.tv_profile_info);
        mTvProfileLccation = findViewById(R.id.tv_profile_location);
        mTvProfileLaguage = findViewById(R.id.tv_profile_language);
        mTvProfileJob = findViewById(R.id.tv_profile_job);
        mTvProfileProvidedInfo = findViewById(R.id.tv_profile_provided_info);
        mTvProfileRegisterDate = findViewById(R.id.tv_profile_register_date);
        mIvBackArrow = findViewById(R.id.iv_profile_back_arrow);
        mIvModifyProfile = findViewById(R.id.iv_profile_pen_modify);
    }

    private void UpdateUI() {
        String imgUrl = mProfile.getProfileImgUrl();
        String title = mProfile.getMyinfo();
        String registerDate = mProfile.getCreatedDate();

        mSvProfilePhoto.setImageURI(Uri.parse(imgUrl));
        if (title != null)
            mTvProfileTitle.setText(title);
        else mTvProfileTitle.setText("프로필 제목 없음");
        mTvProfileRegisterDate.setText("회원 가입" + registerDate);

        mTvProfileLccation.setText("거주지 : " + mProfile.getLocation());
        mTvProfileLaguage.setText("구사 언어 : "+mProfile.getLanguage());
        mTvProfileJob.setText("직장 : "+mProfile.getJob());
        mTvProfileProvidedInfo.setText(mProfile.getFirstName()+"님이 제공한 정보");
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
