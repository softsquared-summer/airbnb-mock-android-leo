package com.shinplest.airbnbclone.src.main.fragment_savelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.main.fragment_savelist.models.SavelistResponse;

import java.util.ArrayList;

public class SavelistAdapter extends RecyclerView.Adapter<SavelistAdapter.SavelistViewHolder> {

    private ArrayList<SavelistResponse.Result> mSavedHouseList;

    public SavelistAdapter(ArrayList<SavelistResponse.Result> savedHouseList) {
        this.mSavedHouseList = savedHouseList;
    }

    public static class SavelistViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSimpleDraweeView;
        private TextView mTvHouseInfo, mTvHouseName, mTvHousePrice, mTvStarAvg, mTvReviewCnt;

        public SavelistViewHolder(@NonNull View view) {
            super(view);
            this.mSimpleDraweeView = view.findViewById(R.id.sv_frag_savelist_room_photo);
            this.mTvHouseInfo = view.findViewById(R.id.tv_frag_savelist_house_info);
            this.mTvHouseName = view.findViewById(R.id.tv_frag_savelist_house_name);
            this.mTvHousePrice = view.findViewById(R.id.tv_frag_savelist_house_price);
            this.mTvStarAvg = view.findViewById(R.id.tv_frag_savelist_house_star_avg);
            this.mTvReviewCnt = view.findViewById(R.id.tv_frag_savelist_house_review_cnt);
        }
    }


    @NonNull
    @Override
    public SavelistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_savelist, parent, false);
        SavelistViewHolder savelistViewHolder = new SavelistViewHolder(holderView);
        return savelistViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavelistViewHolder holder, int position) {
        SavelistResponse.Result savedHouse =  mSavedHouseList.get(position);
        String [] houseImages = savedHouse.getImages().split(",");
        holder.mSimpleDraweeView.setImageURI(houseImages[0]);
        holder.mTvHouseInfo.setText(savedHouse.getHouseInfo());
        holder.mTvHouseName.setText(savedHouse.getHouseName());
        holder.mTvHousePrice.setText(savedHouse.getHousePrice());
        holder.mTvStarAvg.setText(savedHouse.getStarAvg());
        holder.mTvReviewCnt.setText(savedHouse.getReviewCnt());
    }

    @Override
    public int getItemCount() {
        return mSavedHouseList.size();
    }


}
