package com.shinplest.airbnbclone.src.search.interfaces;

        import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

        import java.util.ArrayList;

public interface SearchActivityView {
    void searchSuccess(ArrayList<String> existLocationList, String code, String message);

    void searchFailure();

    void searchHouseSuccess(SimpleHouseInfoResponse simpleHouseInfoResponse);

    void searchHouseFailure(String message);

    void saveHouseSuccess(int code, String message);

    void saveHouseFailure();

    void tryPostSaveHouse(int userNo, int houseNo);

}
