package com.shinplest.airbnbclone.src.search;

import android.util.Log;

import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.interfaces.SearchRetrofitInterface;
import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;

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
                Log.d("test", call.request().url().toString());
                mSearchActivityView.searchSuccess(existLocationResponse.getResults(), existLocationResponse.getCode(), existLocationResponse.getMesssage());
            }

            @Override
            public void onFailure(Call<ExistLocationResponse> call, Throwable t) {
                Log.d("test", call.request().url().toString());
            }
        });


    }
}
