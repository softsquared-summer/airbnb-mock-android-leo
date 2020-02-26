package com.shinplest.airbnbclone.src.search.interfaces;

        import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;
        import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

        import java.util.ArrayList;

public interface SearchActivityView {
    void searchSuccess(ArrayList<ExistLocationResponse.Result> existLocationList, String code, String message);

    void searchFailure();

    void searchHouseSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse);

    void searchHouseFailure(String message);
}
