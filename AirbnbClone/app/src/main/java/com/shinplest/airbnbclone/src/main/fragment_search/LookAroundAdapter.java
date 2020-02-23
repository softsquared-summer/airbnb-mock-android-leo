package com.shinplest.airbnbclone.src.main.fragment_search;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;

public class LookAroundAdapter extends RecyclerView.Adapter<LookAroundAdapter.MyViewHolder> {

    private String[] textSet;
    private String[] urlSet;

    // 생성자
    public LookAroundAdapter(String[] textSet, String[] imgSet) {
        this.textSet = textSet;
        this.urlSet = imgSet;
    }

    // 리사이클러뷰에 들어갈 뷰 홀더, 그리고 그 뷰 홀더에 들어갈 아이템들을 지정
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView simpleDraweeView;
        public TextView textView;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            this.simpleDraweeView = view.findViewById(R.id.sv_frag_search_look_around);
            this.textView = view.findViewById(R.id.tv_frag_search_look_around);
            this.cardView = view.findViewById(R.id.cv_frag_search_continue_look_around);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_look_around, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(this.textSet[position]);
        //fresco로 이미지 넣어주자
        Uri uri = Uri.parse(this.urlSet[position]);
        holder.simpleDraweeView.setImageURI(uri);

        //첫번째와 마지막
        if (position == 0 || position == 4) {
            holder.cardView.getLayoutParams().width = 10;
            holder.cardView.setVisibility(View.INVISIBLE);
        }

    }
    @Override
    public int getItemCount() {
        return textSet.length;
    }


}
