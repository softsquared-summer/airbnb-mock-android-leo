package com.shinplest.airbnbclone.src.main.fragment_search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

public class ContinueLookAroundAdapter extends RecyclerView.Adapter<ContinueLookAroundAdapter.MyViewHolder> {
    private String[] titleSet;
    private String[] contentSet;
    private CardView cardView;

    public ContinueLookAroundAdapter(String[] titleSet, String[] contentSet) {
        this.titleSet = titleSet;
        this.contentSet = contentSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView tvTitle;
        public TextView tvContent;

        public MyViewHolder(View view) {
            super(view);
            this.tvTitle = view.findViewById(R.id.tv_search_frag_continue_look_around_title);
            this.tvContent = view.findViewById(R.id.tv_search_frag_continue_look_around_content);
            this.cardView = view.findViewById(R.id.cv_frag_search_continue_look_around);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_continue_look_around, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.titleSet[position]);
        holder.tvContent.setText(this.contentSet[position]);

        //첫번째와 마지막
        if (position == 0 || position == 4) {
            holder.cardView.getLayoutParams().width = 10;
            holder.cardView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return titleSet.length > contentSet.length ? titleSet.length : contentSet.length;
    }


}
