package com.shinplest.airbnbclone.src.main.fragment_search.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_search.models.AdvantureResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public class SearchFragmentRetrofitInterface {
    @GET("/experiences/1")
    Call<AdvantureResponse> getExperience() {
        return null;
    }
}
