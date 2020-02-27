package com.shinplest.airbnbclone.src.main.fragment_search.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.main.fragment_search.models.SimpleExprerienceResponse;

import java.util.ArrayList;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.MyViewHolder> {
    private ArrayList<SimpleExprerienceResponse.Result> mExperienceList;

    public ExperienceAdapter(ArrayList<SimpleExprerienceResponse.Result> experienceList) {
        this.mExperienceList = experienceList;
    }


    // 리사이클러뷰에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들어갈 아이템들을 지정
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mSvExperiencePhoto;
        public TextView mTvCategory;
        public TextView mTvTitle;
        public TextView mTvPrice;
        public TextView mTvRate;
        public TextView mTvInclude;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mSvExperiencePhoto = itemView.findViewById(R.id.sv_frag_search_holder_experience);
            this.mTvCategory = itemView.findViewById(R.id.tv_frag_search_holder_experience_country);
            this.mTvTitle = itemView.findViewById(R.id.tv_frag_search_holder_experience_title);
            this.mTvPrice = itemView.findViewById(R.id.tv_frag_search_holder_experience_price);
            this.mTvRate = itemView.findViewById(R.id.tv_frag_search_holder_experience_rate);
            this.mTvInclude = itemView.findViewById(R.id.tv_frag_search_holder_experience_include);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_experience, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SimpleExprerienceResponse.Result experiencInfo = mExperienceList.get(position);
        //아직 이미지 없음
        //holder.mSvExperiencePhoto.setImageURI(Uri.parse(experiencInfo.getRepImage()));
        holder.mTvCategory.setText(experiencInfo.getCategoryName());
        holder.mTvTitle.setText(experiencInfo.getExperienceTitle());
        holder.mTvPrice.setText(experiencInfo.getPrice());
        holder.mTvRate.setText(experiencInfo.getStarAvg());
        holder.mTvInclude.setText(experiencInfo.getInfo());
    }

    @Override
    public int getItemCount() {
        return mExperienceList.size();
    }
}
