package com.shinplest.airbnbclone.src.search.interfaces;

        import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;
        import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

public interface SearchActivityView {
    void searchSuccess(ExistLocationResponse.Result existLocationList, int code, String message);

    void searchFailure();

    void searchHouseSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse);

    void searchHouseFailure(String message);
}
