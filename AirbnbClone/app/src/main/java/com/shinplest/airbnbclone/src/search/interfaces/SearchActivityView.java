package com.shinplest.airbnbclone.src.search.interfaces;

import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

public interface SearchActivityView {
    void validateSearchSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse);

    void validateSearchFailure(String message);
}
