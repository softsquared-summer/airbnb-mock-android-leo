package com.shinplest.airbnbclone.src.profile_modify;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileModifyActivityView;
import com.shinplest.airbnbclone.src.profile_modify.models.RequestModifyProfile;

public class ProfileModifyActivity extends BaseActivity implements ProfileModifyActivityView {
    private ProfileResponse.Result mProfile;

    private RequestModifyProfile requestModifyProfile;

    private TextView mTvSaveModifiedProfile;
    private EditText mEtTitle, mEtLocation, mEtSchool, mEtJob, mEtLanguage;
    private SimpleDraweeView mSvProfileImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);
        getUiSource();
        //프로필액티비티로부터 프로필 인텐트 받음
        mProfile = (ProfileResponse.Result) getIntent().getSerializableExtra("Profile");

        //프로필 인텐트로 부터 받은 기본 프로필 체인지
        setBeforeProfile();


        //저장 리퀘 날린다
        mTvSaveModifiedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                //에딧텍스트 내용 전부 set해줌
                requestModifyProfile = new RequestModifyProfile();
                setRequest();

                //수정요청 보냄
                tryPutProfile();
            }
        });


    }

    private void setBeforeProfile() {
        mSvProfileImage.setImageURI(Uri.parse(mProfile.getProfileImgUrl()));
        mEtTitle.setText(mProfile.getMyinfo());
        mEtLocation.setText(mProfile.getLocation());
        mEtLocation.setText(mProfile.getSchool());
        mEtJob.setText(mProfile.getJob());
        mEtLanguage.setText(mProfile.getLanguage());
    }


    private void tryPutProfile() {
        final ProfileModifyService profileModifyService = new ProfileModifyService(this);
        showProgressDialog();
        profileModifyService.putModifiedProfile(requestModifyProfile);
    }

    private void getUiSource() {
        mTvSaveModifiedProfile = findViewById(R.id.tv_profle_modify_save);
        mEtTitle = findViewById(R.id.et_profile_modify_title);
        mEtLocation = findViewById(R.id.et_profile_modify_location);
        mEtSchool = findViewById(R.id.et_profile_modify_school);
        mEtJob = findViewById(R.id.et_profile_modify_job);
        mEtLanguage = findViewById(R.id.et_profile_modify_language);
        mSvProfileImage = findViewById(R.id.sv_profile_modify);
    }

    private void setRequest() {
        //샘플이미지
        requestModifyProfile.setImage("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
        requestModifyProfile.setInfo(mEtTitle.getText().toString());
        requestModifyProfile.setLocation(mEtLocation.getText().toString());
        requestModifyProfile.setSchool(mEtSchool.getText().toString());
        requestModifyProfile.setJob(mEtJob.getText().toString());
        requestModifyProfile.setLanguage(mEtLanguage.getText().toString());
    }

    @Override
    public void validateProfileModifySuccess(String message, int code) {
        hideProgressDialog();
        if (code == 100) {
            showCustomToast(message);
            finish();
        }
    }

    @Override
    public void validateProfileModifyFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
