package com.shinplest.airbnbclone.src.experience;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.experience.interfaces.ExperienceActivityView;
import com.shinplest.airbnbclone.src.experience.models.ExperienceResponse;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class ExperienceActivity extends BaseActivity implements ExperienceActivityView {

    private int mExperienceNo, mIsSave;

    private ViewPager mVpExperienceImages;
    private TextView mTvCategory, mTvTitle, mTvEvaluation, mTvJuso, mTvPlaytime,
            mTvPersonnel, mTvInclusion, mTvOffLanguage, mTvProgram, mTvHostName, mTvHostIntroduce;
    private SimpleDraweeView mSvHostImage;


    private ExperienceResponse.Result mExperienceData;
    private String[] mExperienceDataImages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_TransparentTheme);
        setContentView(R.layout.activity_experience);
        getUiSourse();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mExperienceNo = extras.getInt("experienceNo");
        mIsSave = extras.getInt("isSave");

        tryGetExperienceInfo(mExperienceNo);
    }

    void getUiSourse(){
        mVpExperienceImages = findViewById(R.id.vp_experience);
        mTvCategory = findViewById(R.id.tv_experience_category);
        mTvTitle = findViewById(R.id.tv_experience_title);
        mTvEvaluation = findViewById(R.id.tv_experience_evaluation);
        mTvJuso = findViewById(R.id.tv_experience_juso);
        mTvPersonnel= findViewById(R.id.tv_experience_playtime);
        mTvInclusion = findViewById(R.id.tv_experience_inclusion);
        mTvOffLanguage = findViewById(R.id.tv_experience_offLanguage);
        mTvProgram = findViewById(R.id.tv_experinece_program);
        mTvHostName = findViewById(R.id.tv_experience_host_name);
        mTvHostIntroduce = findViewById(R.id.tv_experience_host_introduce);
        mSvHostImage = findViewById(R.id.sv_experience_host_img);

    }

    void updateUi(){
        mExperienceDataImages = mExperienceData.getImage().get(0).getImageUrl().split(",");
        mVpExperienceImages.setAdapter(new ExperienceViewPageAdatper(this, mExperienceDataImages));
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.experience_dots_indicator);
        wormDotsIndicator.setViewPager(mVpExperienceImages);


        ExperienceResponse.Info experienceDataInfo = mExperienceData.getInfo();
        mTvCategory.setText(experienceDataInfo.getCategory());
        mTvTitle.setText(experienceDataInfo.getTitle());
        mTvEvaluation.setText("★"+experienceDataInfo.getEvaluation());
        mTvJuso.setText(experienceDataInfo.getJuso());
        mTvPersonnel.setText(experienceDataInfo.getPlaytime());
        mTvPersonnel.setText(experienceDataInfo.getPersonnel());
        mTvInclusion.setText(experienceDataInfo.getInclusion());
        mTvOffLanguage.setText(experienceDataInfo.getOfferLanguage());
        mTvProgram.setText(experienceDataInfo.getProgram());
        mTvHostName.setText(experienceDataInfo.getHostName());
        mTvHostIntroduce.setText(experienceDataInfo.getHostIntroduce());
        mSvHostImage.setImageURI(Uri.parse(experienceDataInfo.getHostImage()));
    }

    void tryGetExperienceInfo(int experienceNo){
        showProgressDialog();
        final ExperienceService experienceService = new ExperienceService(this);
        experienceService.getExperienceInfo(experienceNo);
    }

    @Override
    public void getExperienceSuccess(ExperienceResponse.Result experienceResult, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            mExperienceData = experienceResult;
            updateUi();
        }
        else
            showCustomToast("실패");
    }

    @Override
    public void getExperienceFailure(String message) {
        hideProgressDialog();
        showCustomToast("실패");
    }
}
