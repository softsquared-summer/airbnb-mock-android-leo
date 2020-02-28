package com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import java.util.ArrayList;

public interface SavelistFragmentView {
    void getSavedHouseListSuccess(ArrayList<SavelistResponse.Result> savedHouseList, int code, String message);

    void getSavedHouseListFailure(String message);
}
