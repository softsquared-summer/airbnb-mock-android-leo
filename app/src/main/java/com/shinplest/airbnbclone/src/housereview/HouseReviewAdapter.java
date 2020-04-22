package com.shinplest.airbnbclone.src.housereview;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.housereview.models.HouseReviewResponse;

import java.util.ArrayList;

import static android.view.View.GONE;

public class HouseReviewAdapter extends RecyclerView.Adapter<HouseReviewAdapter.MyViewHolder> {

    private ArrayList<HouseReviewResponse.Review> mHouseReviewList;

    public HouseReviewAdapter(ArrayList<HouseReviewResponse.Review> houseReviewList) {
        this.mHouseReviewList = houseReviewList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTvGuestName, mTvDate, mTvContent, mTvHostReply;
        SimpleDraweeView mSvGuestImg;
        View mViewLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvGuestName = itemView.findViewById(R.id.tv_house_review_holder_guest_name);
            this.mTvDate = itemView.findViewById(R.id.tv_house_review_holder_date);
            this.mTvContent = itemView.findViewById(R.id.tv_house_review_holder_review_content);
            this.mTvHostReply = itemView.findViewById(R.id.tv_house_review_holder_host_reply);
            this.mSvGuestImg = itemView.findViewById(R.id.sv_house_review_holder_guest_img);
            this.mViewLine = itemView.findViewById(R.id.line_above_host_reply);
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
        holder.mSvGuestImg.setImageURI(Uri.parse(review.getGuestImg()));
        holder.mTvGuestName.setText(review.getGuestName());
        holder.mTvDate.setText(review.getDate());
        holder.mTvContent.setText(review.getReviewContent());
        //호스트 답변없으면 안써줌
        if (review.getHostReply() == null) {
            holder.mTvHostReply.setVisibility(GONE);
            holder.mViewLine.setVisibility(GONE);
        } else {
            holder.mTvHostReply.setText(review.getHostReply());
        }
    }

    @Override
    public int getItemCount() {
        return mHouseReviewList.size();
    }

}
