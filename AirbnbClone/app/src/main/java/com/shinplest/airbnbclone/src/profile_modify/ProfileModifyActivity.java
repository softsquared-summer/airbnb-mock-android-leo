package com.shinplest.airbnbclone.src.profile_modify;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseActivity;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileModifyActivityView;
import com.shinplest.airbnbclone.src.profile_modify.models.RequestModifyProfile;

import org.w3c.dom.Text;

public class ProfileModifyActivity extends BaseActivity implements ProfileModifyActivityView {
    private RequestModifyProfile requestModifyProfile;

    private TextView mTvSaveModifiedProfile;
    private EditText mEtTitle, mEtLocation, mEtSchool, mEtJob, mEtLanguage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);
        getUiSource();

        //저장 리퀘 날린다
        mTvSaveModifiedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //에딧텍스트 내용 전부 set해줌
                requestModifyProfile = new RequestModifyProfile();
                setRequest();
            }
        });


    }

    private void getUiSource() {
        mTvSaveModifiedProfile = findViewById(R.id.tv_profle_modify_save);
        mEtTitle = findViewById(R.id.et_profile_modify_title);
        mEtLocation = findViewById(R.id.et_profile_modify_location);
        mEtSchool = findViewById(R.id.et_profile_modify_school);
        mEtLanguage = findViewById(R.id.et_profile_modify_language);
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
    public void validateJwtLoginSuccess(String message, int code) {
        hideProgressDialog();
        if (code == 100) {

        }
    }

    @Override
    public void validateJwtLoginFailure(String message) {
        hideProgressDialog();
        showCustomToast(message == null || message.isEmpty() ? getString(R.string.network_error) : message);
    }
}
