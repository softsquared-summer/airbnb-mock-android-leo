package com.shinplest.airbnbclone.src.main.fragment_travel;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.main.fragment_travel.models.ReservedResponse;

import java.util.ArrayList;

public class PastReservedHouseAdapter extends RecyclerView.Adapter<PastReservedHouseAdapter.MyViewHolder> {
    private ArrayList<ReservedResponse.PastReservationList> mPastReservationLists;
    private Context context;

    public PastReservedHouseAdapter(ArrayList<ReservedResponse.PastReservationList> mPastReservationLists, Context context) {
        this.mPastReservationLists = mPastReservationLists;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView mSvReservedHousePhoto;
        public TextView mTvReservedDate, mTvReservedHouseName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mSvReservedHousePhoto = itemView.findViewById(R.id.sv_holder_reserved_house_img);
            this.mTvReservedHouseName = itemView.findViewById(R.id.tv_holder_reserved_house_name);
            this.mTvReservedDate = itemView.findViewById(R.id.tv_holder_reserved_date);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_reserved_house, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ReservedResponse.PastReservationList reservation = mPastReservationLists.get(position);
        holder.mSvReservedHousePhoto.setImageURI(Uri.parse(reservation.getImageUrl()));
        holder.mTvReservedHouseName.setText(reservation.getName());
        holder.mTvReservedDate.setText(reservation.getDate());
    }

    @Override
    public int getItemCount() {
        return mPastReservationLists.size();
    }


}
