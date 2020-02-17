package com.shinplest.airbnbclone.src.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.MyViewHolder> {

    private String[] textSet;
    private int[] imgSet;

    // 생성자
    public AdapterCard(String[] textSet, int[] imgSet){
        this.textSet = textSet;
        this.imgSet = imgSet;
    }

    // 리사이클러뷰에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들어갈 아이템들을 지정
    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View view){
            super(view);
            this.imageView = view.findViewById(R.id.iv_pic);
            this.textView = view.findViewById(R.id.tv_text);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(this.textSet[position]);
        holder.imageView.setBackgroundResource(this.imgSet[position]);
        //fresco로 이미지 넣어주자
    }

    @Override
    public int getItemCount() {
        return textSet.length > imgSet.length ? textSet.length : imgSet.length;
    }


}
