package com.shinplest.airbnbclone.src.main.fragment_search.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.experience.ExperienceActivity;
import com.shinplest.airbnbclone.src.house.HouseActivity;
import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

import java.util.ArrayList;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {
    private ArrayList<SimpleExprerienceResponse.Result> mExperienceList;
    private Context context;

    public ExperienceAdapter(ArrayList<SimpleExprerienceResponse.Result> mExperienceList, Context context) {
        this.mExperienceList = mExperienceList;
        this.context = context;
    }

    // 리사이클러뷰에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들어갈 아이템들을 지정
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mSvExperiencePhoto;
        public TextView mTvCategory;
        public TextView mTvTitle;
        public TextView mTvPrice;
        public TextView mTvRate;
        public TextView mTvReviewCnt;
        public TextView mTvInclude;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mSvExperiencePhoto = itemView.findViewById(R.id.sv_frag_search_holder_experience);
            this.mTvCategory = itemView.findViewById(R.id.tv_frag_search_holder_experience_country);
            this.mTvTitle = itemView.findViewById(R.id.tv_frag_search_holder_experience_title);
            this.mTvPrice = itemView.findViewById(R.id.tv_frag_search_holder_experience_price);
            this.mTvRate = itemView.findViewById(R.id.tv_frag_search_holder_experience_rate);
            this.mTvInclude = itemView.findViewById(R.id.tv_frag_search_holder_experience_include);
            this.mTvReviewCnt = itemView.findViewById(R.id.tv_frag_search_holder_review_cnt);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_experience, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }


    //클릭했을때 넘겨줌
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final SimpleExprerienceResponse.Result experience = mExperienceList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int experienceNo = experience.getExperienceNo();
                int isSave = experience.getIsSave();
                Log.d("OnclickTest", "onClick: " + experienceNo);
                //여기서 experience 넘버로 새로운 액티비티로 넘어가줘야함
                Intent intent = new Intent(v.getContext(), ExperienceActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("experienceNo", experienceNo);
                extras.putInt("isSave", isSave);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        holder.mSvExperiencePhoto.setImageURI(Uri.parse(experience.getRepImage()));
        holder.mTvCategory.setText(experience.getCategoryName());
        holder.mTvTitle.setText(experience.getExperienceTitle());
        holder.mTvPrice.setText(experience.getExperiencePrice());
        holder.mTvRate.setText("★"+experience.getStarAvg());
        holder.mTvReviewCnt.setText("("+experience.getReviewcnt()+")");
        holder.mTvInclude.setText(experience.getExperienceInfo());
    }

    @Override
    public int getItemCount() {
        return mExperienceList.size();
    }
}
