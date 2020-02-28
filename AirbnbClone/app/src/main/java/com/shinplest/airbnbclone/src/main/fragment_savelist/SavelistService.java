package com.shinplest.airbnbclone.src.main.fragment_savelist;

import android.util.Log;

import com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces.SavelistFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces.SavelistRetrofitInterface;
import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class SavelistService {
    private final SavelistFragmentView mSavelistFragmentView;

    public SavelistService(SavelistFragmentView savelistFragmentView) {
        this.mSavelistFragmentView = savelistFragmentView;
    }

    void getSaveList(int userNo) {
        final SavelistRetrofitInterface savelistRetrofitInterface = getRetrofit().create(SavelistRetrofitInterface.class);
        savelistRetrofitInterface.getSavelist(userNo, "detail", "").enqueue(new Callback<SavelistResponse>() {
            @Override
            public void onResponse(Call<SavelistResponse> call, Response<SavelistResponse> response) {
                Log.d("testtesttest", call.request().url().toString());
                final SavelistResponse savelistResponse = response.body();
                mSavelistFragmentView.getSavedHouseListSuccess(savelistResponse.getResult(), savelistResponse.getCode(), savelistResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SavelistResponse> call, Throwable t) {
                Log.d("testtesttest", call.request().url().toString());

            }
        });
    }
}
