package com.shinplest.airbnbclone.src.search;

import android.util.Log;

import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.interfaces.SearchRetrofitInterface;
import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class SearchService {
    private final SearchActivityView mSearchActivityView;

    public SearchService(SearchActivityView searchActivityView) {
        this.mSearchActivityView = searchActivityView;
    }

    void getExistLocationList(String searchWord){
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);
        Call<ExistLocationResponse> call = searchRetrofitInterface.getExistLocation("house",searchWord);
        call.enqueue(new Callback<ExistLocationResponse>() {
            @Override
            public void onResponse(Call<ExistLocationResponse> call, Response<ExistLocationResponse> response) {
                ExistLocationResponse existLocationResponse = response.body();
                mSearchActivityView.searchSuccess(existLocationResponse.getResults(), existLocationResponse.getCode(), existLocationResponse.getMesssage());
            }

            @Override
            public void onFailure(Call<ExistLocationResponse> call, Throwable t) {
            }
        });
    }

    void getSimpleHouseInfo(String searchWord){
        final SearchRetrofitInterface searchRetrofitInterface = getRetrofit().create(SearchRetrofitInterface.class);

        HashMap<String, String> params = new HashMap<>();
        params.put("search", searchWord);
        params.put("guest", "0");
        params.put("houseType", "");
        params.put("bed", "0");
        params.put("room", "0");
        params.put("bathroom", "0");
        params.put("facilities", "");
        params.put("buildingType", "");
        params.put("rule", "");
        params.put("location", "");
        params.put("language", "");

        Call<SimpleHouseInfoResponse> call = searchRetrofitInterface.getSimpleHouseInfo(params);
        call.enqueue(new Callback<SimpleHouseInfoResponse>() {
            @Override
            public void onResponse(retrofit2.Call<SimpleHouseInfoResponse> call, Response<SimpleHouseInfoResponse> response) {
                final SimpleHouseInfoResponse simpleHouseInfoResponse = response.body();
                mSearchActivityView.searchHouseSuccess(simpleHouseInfoResponse);
                Log.d("network", call.request().url().toString());
            }

            @Override
            public void onFailure(retrofit2.Call<SimpleHouseInfoResponse> call, Throwable t) {
                Log.d("network", "getfail");
                Log.d("network", call.request().url().toString());
            }
        });
    }
}
