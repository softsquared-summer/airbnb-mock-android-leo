package com.shinplest.airbnbclone.src.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;

import java.util.ArrayList;

public class HousesAdapter extends RecyclerView.Adapter<HousesAdapter.MyViewHolder> {
    private ArrayList<SimpleHouseInfoResponse.Result> mHouseDataList;

    public HousesAdapter(ArrayList<SimpleHouseInfoResponse.Result> houseDataList) {
        this.mHouseDataList = houseDataList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ViewPager mVpHousePhotos;
        private TextView mTvHouseInfo, mTvStarAvg, mTvHouseName;

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.mVpHousePhotos = view.findViewById(R.id.vp_house_holder);
            this.mTvHouseInfo = view.findViewById(R.id.tv_house_holder_house_info);
            this.mTvStarAvg = view.findViewById(R.id.tv_house_holder_star_avg);
            this.mTvHouseName = view.findViewById(R.id.tv_house_holder_house_name);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_house, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SimpleHouseInfoResponse.Result house = mHouseDataList.get(position);
        holder.mTvHouseInfo.setText(house.getHouseInfo());
        holder.mTvStarAvg.setText(house.getStarAvg());
        holder.mTvHouseName.setText(house.getHouseName());
    }

    @Override
    public int getItemCount() {
        return mHouseDataList.size();
    }

}
