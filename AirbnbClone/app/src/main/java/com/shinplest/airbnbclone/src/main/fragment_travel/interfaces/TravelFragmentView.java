package com.shinplest.airbnbclone.src.main.fragment_travel.interfaces;

import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

public interface TravelFragmentView {
    void getReservedHouseSuccess(ReservedResponse.Result result, int code, String message);

    void getReservedHouseFailure(String message);
}
