package com.shinplest.airbnbclone.src.house;

import android.util.Log;

import com.shinplest.airbnbclone.src.house.interfaces.HouseActivityView;
import com.shinplest.airbnbclone.src.house.interfaces.HouseRetrofitInterface;
import com.shinplest.airbnbclone.src.house.models.HouseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class HouseService {
    private final HouseActivityView mHouseActivityView;

    public HouseService(HouseActivityView houseActivityView) {
        this.mHouseActivityView = houseActivityView;
    }

    void getHouseinfo(int houseNo){
        final HouseRetrofitInterface houseRetrofitInterface = getRetrofit().create(HouseRetrofitInterface.class);
        houseRetrofitInterface.getHouseInfo(houseNo).enqueue(new Callback<HouseResponse>() {
            @Override
            public void onResponse(Call<HouseResponse> call, Response<HouseResponse> response) {
                Log.d("testtesttest", call.request().url().toString());
                final HouseResponse houseResponse = response.body();
                //데이터가 많아서 통째로 보내줌
                mHouseActivityView.getHouseSuccess(houseResponse.getResult(), houseResponse.getCode(),houseResponse.getMessage() );
            }

            @Override
            public void onFailure(Call<HouseResponse> call, Throwable t) {
                Log.d("testtesttest", call.request().url().toString());
                mHouseActivityView.getHouseFailure(null);
            }
        });

    }
}
