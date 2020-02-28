package com.shinplest.airbnbclone.src.main.fragment_savelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.general.BaseFragment;
import com.shinplest.airbnbclone.src.main.fragment_savelist.interfaces.SavelistFragmentView;
import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import java.util.ArrayList;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;

public class SavelistFragment extends BaseFragment implements SavelistFragmentView {

    private RecyclerView mRvSavelist;
    private SavelistAdapter mSaveListAdapter;
    private ArrayList<SavelistResponse.Result> mSavedHouseList;

    public SavelistFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savelist, container, false);
        //getUiSource
        mRvSavelist = view.findViewById(R.id.rv_frag_savelist);
        //네트워크 통신
        tryGetSavedHouseList(USER_NO);
        //RecyclerView 초기화
        mRvSavelist.setHasFixedSize(true);
        mRvSavelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSavedHouseList = new ArrayList<>();
        mSaveListAdapter = new SavelistAdapter(mSavedHouseList);
        mRvSavelist.setAdapter(mSaveListAdapter);

        return view;
    }

    void tryGetSavedHouseList(int userNo){
        final SavelistService savelistService = new SavelistService(this);
        showProgressDialog();
        savelistService.getSaveList(userNo);
    }

    @Override
    public void getSavedHouseListSuccess(ArrayList<SavelistResponse.Result> savedHouseList, int code, String message) {
        hideProgressDialog();
        if(code == 100){
            mSavedHouseList.clear();
            mSavedHouseList.addAll(savedHouseList);
            mSaveListAdapter.notifyDataSetChanged();
        }
        showCustomToastFrag(message);
    }

    @Override
    public void getSavedHouseListFailure(String message) {

    }
}
