package com.shinplest.airbnbclone.src.main.fragment_travel;

import android.util.Log;

import com.shinplest.airbnbclone.src.main.fragment_travel.interfaces.TravelFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_travel.interfaces.TravelRetrofitInterface;
import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class TravelService {

    private final TravelFragmentView mTravelFragmentView;

    public TravelService(TravelFragmentView travelFragmentView) {
        this.mTravelFragmentView = travelFragmentView;
    }

    void getReservedHouse(int userNo){
        final TravelRetrofitInterface travelRetrofitInterface = getRetrofit().create(TravelRetrofitInterface.class);
        travelRetrofitInterface.getReservedResponse(userNo).enqueue(new Callback<ReservedResponse>() {
            @Override
            public void onResponse(Call<ReservedResponse> call, Response<ReservedResponse> response) {
                final ReservedResponse reservedResponse = response.body();
                mTravelFragmentView.getReservedHouseSuccess(reservedResponse.getResult(), reservedResponse.getCode(), reservedResponse.getMessage());
            }

            @Override
            public void onFailure(Call<ReservedResponse> call, Throwable t) {
                Log.d("test", "예약정보 조회 실패");
            }
        });
    }
}
