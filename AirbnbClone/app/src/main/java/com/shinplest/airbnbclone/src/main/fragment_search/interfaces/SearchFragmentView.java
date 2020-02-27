package com.shinplest.airbnbclone.src.main.fragment_search.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

import java.util.ArrayList;

public interface SearchFragmentView {
    void getExperiencessSuccess(ArrayList<SimpleExprerienceResponse.Result> simpleExprerienceResponseResultList, int code, String message);

    void getExperiencessFailure(String message);

}
