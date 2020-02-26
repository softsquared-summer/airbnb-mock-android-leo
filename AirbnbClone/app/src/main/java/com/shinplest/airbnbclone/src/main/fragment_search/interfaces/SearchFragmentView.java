package com.shinplest.airbnbclone.src.main.fragment_search.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

public interface SearchFragmentView {
    void searchHouseSuccess(SimpleExprerienceResponse simpleExprerienceResponse);

    void searchHouseFailure(String message);

}
