package com.shinplest.airbnbclone.src.main.fragment_travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseFragment;
import com.shinplest.airbnbclone.src.main.fragment_travel.interfaces.TravelFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;

public class TravelFragment extends BaseFragment implements TravelFragmentView {

    public TravelFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);

        //getUiSource;

        tryGetReservedHouses(USER_NO);


        return view;

    }

    void updateUi(){

    }

    void tryGetReservedHouses(int userNO){
        showProgressDialog();
        final TravelService travelService = new TravelService(this);
        travelService.getReservedHouse(userNO);
    }

    @Override
    public void getReservedHouseSuccess(ReservedResponse.Result result, int code, String message) {
        hideProgressDialog();
        if (code == 100) {
            //널이아닐때만 해줘야함 데이터가 있을경우만 널익셉션 해주자
            showCustomToastFrag(result.getReservationList().get(0).getName());
        }
    }

    @Override
    public void getReservedHouseFailure(String message) {
        hideProgressDialog();
        showCustomToastFrag("fail");
    }
}
