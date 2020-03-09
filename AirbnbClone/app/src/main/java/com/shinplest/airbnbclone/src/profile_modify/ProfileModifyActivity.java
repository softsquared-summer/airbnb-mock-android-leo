package com.shinplest.airbnbclone.src.profile_modify;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseActivity;
import com.shinplest.airbnbclone.src.profile.models.ProfileResponse;
import com.shinplest.airbnbclone.src.profile_modify.interfaces.ProfileModifyActivityView;
import com.shinplest.airbnbclone.src.profile_modify.models.RequestModifyProfile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.FROM_ALBUM;
import static com.shinplest.airbnbclone.src.general.ApplicationClass.FROM_CAMERA;

public class ProfileModifyActivity extends BaseActivity implements ProfileModifyActivityView {
    private ProfileResponse.Result mProfile;

    private RequestModifyProfile requestModifyProfile;

    private TextView mTvSaveModifiedProfile;
    private EditText mEtTitle, mEtLocation, mEtSchool, mEtJob, mEtLanguage;
    private SimpleDraweeView mSvProfileImage;
    private ImageView mIvCamera;

    private String mCurrentPhotoPath;
    private Uri imgUri, photoURI, albumURI;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);
        getUiSource();
        //프로필액티비티로부터 프로필 인텐트 받음
        //널이 아닐때만 받아줌, 액티비티 처음 시작될때 아닐때 받아오는거 방지
        if (getIntent().getSerializableExtra("Profile") != null)
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

        //카메라 불러올건지 저장목록 불러올건지 물어본다.
        mIvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCameraAlertDialog();
            }
        });


        ///사진

        //TedPermission 라이브러리 -> 카메라 권한 획득

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                showCustomToast("Permission Granted");
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                showCustomToast("Permission Denied\n" + deniedPermissions.toString());
            }
        };
    }


    private void setBeforeProfile() {
        mSvProfileImage.setImageURI(Uri.parse(mProfile.getProfileImgUrl()));
        mEtTitle.setText(mProfile.getMyinfo());
        mEtLocation.setText(mProfile.getLocation());
        mEtSchool.setText(mProfile.getSchool());
        mEtJob.setText(mProfile.getJob());
        mEtLanguage.setText(mProfile.getLanguage());
    }


    private void tryPutProfile() {
        final ProfileModifyService profileModifyService = new ProfileModifyService(this);
        showProgressDialog();
        profileModifyService.putModifiedProfile(requestModifyProfile);
    }

    private void makeCameraAlertDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("사진 찍기").setCancelable(false)
                .setMessage("사진을 새로찍으시거나 사진 라이브러리에서 선택하세요.")
                .setPositiveButton("카메라",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 사진 촬영 클릭
                                showCustomToast("구현 되지 않은 기능입니다.");
                                //takePhoto();
                            }
                        }).setNegativeButton("갤러리", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int id) {
                //앨범에서 선택
                selectAlbum();
            }
        });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    //앨범 선택 클릭
    private void selectAlbum() {
        //앨범 열기
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent, 123);
    }

    private void takePhoto() {
        // 촬영 후 이미지 가져옴
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (photoFile != null) {
                    Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                    imgUri = providerURI;
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, providerURI);
                    startActivityForResult(intent, FROM_CAMERA);
                }
            }
        } else {
            Log.d("알림", "저장공간에 접근 불가능");
            return;
        }
    }

    public File createImageFile() throws IOException {
        String imgFileName = System.currentTimeMillis() + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "ireh");
        if (!storageDir.exists()) {
            //없으면 만들기
            Log.d("알림", "storageDir 존재 x " + storageDir.toString());
            storageDir.mkdirs();
        }
        Log.d("알림", "storageDir 존재함 " + storageDir.toString());
        imageFile = new File(storageDir, imgFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();
        return imageFile;
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        showCustomToast("사진이 저장되었습니다");
    }

    private void getUiSource() {
        mTvSaveModifiedProfile = findViewById(R.id.tv_profle_modify_save);
        mEtTitle = findViewById(R.id.et_profile_modify_title);
        mEtLocation = findViewById(R.id.et_profile_modify_location);
        mEtSchool = findViewById(R.id.et_profile_modify_school);
        mEtJob = findViewById(R.id.et_profile_modify_job);
        mEtLanguage = findViewById(R.id.et_profile_modify_language);
        mSvProfileImage = findViewById(R.id.sv_profile_modify);
        mIvCamera = findViewById(R.id.iv_profile_modify_camera);
    }

    private void setRequest() {
        //샘플이미지
        //여기에 파이어 베이스 url입력해야 함
        requestModifyProfile.setImage("https://cdn.pixabay.com/photo/2020/01/13/19/27/victor-emmanuel-ii-monument-4763299_960_720.jpg");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case FROM_ALBUM: {
                //앨범에서 가져오기
                if (data.getData() != null) {
                    try {
                        File albumFile = null;
                        albumFile = createImageFile();
                        photoURI = data.getData();
                        albumURI = Uri.fromFile(albumFile);
                        galleryAddPic();
                        //이미지뷰에 이미지 셋팅
                        mIvCamera.setImageURI(photoURI);
                        //cropImage();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("알림", "앨범에서 가져오기 에러");
                    }
                }
                break;
            }
            case FROM_CAMERA: {
                //촬영
                try {
                    Log.d("알림", "FROM_CAMERA 처리");
                    galleryAddPic();
                    //이미지뷰에 이미지셋팅
                    mSvProfileImage.setImageURI(imgUri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
