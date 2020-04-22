package com.shinplest.airbnbclone.src.main.fragment_search.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchFragmentRetrofitInterface {
    @GET("/experiences")
    Call<SimpleExprerienceResponse> getSimpleExperienceInfo(@QueryMap Map<String, String> querymap);
}
