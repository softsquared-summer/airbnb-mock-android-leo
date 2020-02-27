package com.shinplest.airbnbclone.src.main.fragment_search;

import com.shinplest.airbnbclone.src.main.fragment_search.interfaces.SearchFragmentRetrofitInterface;
import com.shinplest.airbnbclone.src.main.fragment_search.interfaces.SearchFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.getRetrofit;

public class SearchFragmentService {

    private final SearchFragmentView mSearchFragmentView;

    public SearchFragmentService(SearchFragmentView mSearchFragmentView) {
        this.mSearchFragmentView = mSearchFragmentView;
    }

    void getSimplerExperienceInfo(){
        final SearchFragmentRetrofitInterface searchFragmentRetrofitInterface = getRetrofit().create(SearchFragmentRetrofitInterface.class);

        HashMap<String, String> params = new HashMap<>();
        params.put("search", "");
        params.put("guest", "");
        params.put("priceMin", "");
        params.put("priceMax", "");
        params.put("time", "");
        params.put("language", "");

        Call<SimpleExprerienceResponse> call = searchFragmentRetrofitInterface.getSimpleExperienceInfo(params);
        call.enqueue(new Callback<SimpleExprerienceResponse>() {
            @Override
            public void onResponse(Call<SimpleExprerienceResponse> call, Response<SimpleExprerienceResponse> response) {
                final SimpleExprerienceResponse simpleExprerienceResponse = response.body();
                mSearchFragmentView.getExperiencessSuccess(simpleExprerienceResponse.getResult(), simpleExprerienceResponse.getCode(), simpleExprerienceResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SimpleExprerienceResponse> call, Throwable t) {

            }
        });

    }


}
