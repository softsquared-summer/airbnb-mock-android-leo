package com.shinplest.airbnbclone.src.main.fragment_travel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseFragment;
import com.shinplest.airbnbclone.src.main.fragment_travel.interfaces.TravelFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

import java.util.ArrayList;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;

public class TravelFragment extends BaseFragment implements TravelFragmentView {

    private RecyclerView mRvReservedHouse, mRvPastReservedHouse;

    private ArrayList<ReservedResponse.ReservationList> mReservedHouseList;
    private ArrayList<ReservedResponse.PastReservationList> mPastReservedHouseList;

    private ReservedHouseAdapter mReservedHouseAdapter;
    private PastReservedHouseAdapter mPastReservedHouseAdapter;

    private ImageView mIvNoTravel;

    public TravelFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, container, false);
        //getUiSource;
        mRvReservedHouse = view.findViewById(R.id.rv_frag_travel_reserved_house);
        mRvPastReservedHouse = view.findViewById(R.id.rv_frag_travel_past_reserved_house);
        mIvNoTravel = view.findViewById(R.id.iv_frag_travel_no_travel);


        mRvReservedHouse.setHasFixedSize(true);
        mRvReservedHouse.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mReservedHouseList = new ArrayList<>();
        mReservedHouseAdapter = new ReservedHouseAdapter(mReservedHouseList,getContext());
        mRvReservedHouse.setAdapter(mReservedHouseAdapter);
        mRvReservedHouse.addItemDecoration(new GridItemDecoration(10));

        mRvPastReservedHouse.setHasFixedSize(true);
        mRvPastReservedHouse.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPastReservedHouseList = new ArrayList<>();
        mPastReservedHouseAdapter = new PastReservedHouseAdapter(mPastReservedHouseList,getContext());
        mRvPastReservedHouse.setAdapter(mPastReservedHouseAdapter);
        mRvPastReservedHouse.addItemDecoration(new GridItemDecoration(10));

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
            mIvNoTravel.setVisibility(View.GONE);
            mReservedHouseList.addAll(result.getReservationList());
            mPastReservedHouseList.addAll(result.getPastReservationList());
            mReservedHouseAdapter.notifyDataSetChanged();
            mPastReservedHouseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getReservedHouseFailure(String message) {
        hideProgressDialog();
        showCustomToastFrag("fail");
    }
}
