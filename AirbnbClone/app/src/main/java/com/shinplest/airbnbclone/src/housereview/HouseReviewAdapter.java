package com.shinplest.airbnbclone.src.housereview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

import java.util.ArrayList;

public class HouseReviewAdapter extends RecyclerView.Adapter<HouseReviewAdapter.MyViewHolder> {

    private ArrayList<HouseReviewResponse.Review> mHouseReviewList;

    public HouseReviewAdapter(ArrayList<HouseReviewResponse.Review> houseReviewList) {
        this.mHouseReviewList = houseReviewList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView mTvGuestName, mTvDate, mTvContent, mTvHostReply;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvGuestName = itemView.findViewById(R.id.tv_house_review_holder_guest_name);
            this.mTvDate = itemView.findViewById(R.id.tv_house_review_holder_date);
            this.mTvContent = itemView.findViewById(R.id.tv_house_review_holder_review_content);
            this.mTvHostReply = itemView.findViewById(R.id.tv_house_review_holder_host_reply);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holdervView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_house_review, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holdervView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final HouseReviewResponse.Review review = mHouseReviewList.get(position);

        holder.mTvGuestName.setText(review.getGuestName());
        holder.mTvDate.setText(review.getDate());
        holder.mTvContent.setText(review.getReviewContent());
        holder.mTvHostReply.setText(review.getHostReply());
    }

    @Override
    public int getItemCount() {
        return mHouseReviewList.size();
    }

}
