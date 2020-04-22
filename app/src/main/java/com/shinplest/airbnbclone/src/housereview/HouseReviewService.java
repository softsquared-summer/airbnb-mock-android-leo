package com.shinplest.airbnbclone.src.housereview;

import android.util.Log;

import com.shinplest.airbnbclone.src.housereview.interfaces.HouseReviewActivityView;
import com.shinplest.airbnbclone.src.housereview.interfaces.HouseReviewRetrofitInterface;
import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class HouseReviewService {

    private final HouseReviewActivityView mHouseReviewActivityView;

    public HouseReviewService(HouseReviewActivityView houseReviewActivityView) {
        this.mHouseReviewActivityView = houseReviewActivityView;
    }

    void getHouseReviews(int houseNo) {
        final HouseReviewRetrofitInterface houseReviewRetrofitInterface = getRetrofit().create(HouseReviewRetrofitInterface.class);
        houseReviewRetrofitInterface.getHouseReviews(houseNo).enqueue(new Callback<HouseReviewResponse>() {
            @Override
            public void onResponse(Call<HouseReviewResponse> call, Response<HouseReviewResponse> response) {
                Log.d("testtesttest", call.request().url().toString());
                final HouseReviewResponse houseReviewResponse = response.body();
                if (houseReviewResponse != null)
                    mHouseReviewActivityView.getHouseReviewSuccess(houseReviewResponse.getResult(), houseReviewResponse.getCode(), houseReviewResponse.getMessage());
                else
                    mHouseReviewActivityView.getHouseReviewFailure("이 숙소는 아직 리뷰가 없습니다.");
            }

            @Override
            public void onFailure(Call<HouseReviewResponse> call, Throwable t) {
                Log.d("testtesttest", call.request().url().toString());
                mHouseReviewActivityView.getHouseReviewFailure(null);

            }
        });
    }
}
