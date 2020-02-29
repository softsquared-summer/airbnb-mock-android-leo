package com.shinplest.airbnbclone.src.house;

import android.util.Log;

import com.shinplest.airbnbclone.src.house.interfaces.HouseActivityView;
import com.shinplest.airbnbclone.src.house.interfaces.HouseRetrofitInterface;
import com.shinplest.airbnbclone.src.house.models.HouseResponse;
import com.shinplest.airbnbclone.src.main.models.DefaultResponse;
import com.shinplest.airbnbclone.src.search.interfaces.SearchRetrofitInterface;
import com.shinplest.airbnbclone.src.search.models.RequestSaveHouse;

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
                final HouseResponse houseResponse = response.body();
                //데이터가 많아서 통째로 보내줌
                mHouseActivityView.getHouseSuccess(houseResponse.getResult(), houseResponse.getCode(),houseResponse.getMessage() );
            }

            @Override
            public void onFailure(Call<HouseResponse> call, Throwable t) {
                mHouseActivityView.getHouseFailure(null);
            }
        });
    }


    //서치에 있는 api갖다 씀
    void postSaveHouse(int userNo, int houseNo){
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        RequestSaveHouse requestSaveHouse = new RequestSaveHouse();
        requestSaveHouse.setHouseNo(houseNo);
        Call<DefaultResponse> call = searchRetrofitInterface.postHouseSave(userNo, requestSaveHouse);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse =response.body();
                mHouseActivityView.saveHouseSuccess(defaultResponse.getCode(), defaultResponse.getMessage());
            }
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
            }
        });
    }

    void deleteSavedHouse(int userNo, int houseNo){
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        Call<DefaultResponse> call = searchRetrofitInterface.deleteSavedHouse(userNo, houseNo);
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                DefaultResponse defaultResponse =response.body();
                mHouseActivityView.deleteHouseSuccess(defaultResponse.getCode(), defaultResponse.getMessage());
            }
            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }
}
