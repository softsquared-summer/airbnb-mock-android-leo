package com.shinplest.airbnbclone.src.main.fragment_savelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;

public class SavelistAdapter extends RecyclerView.Adapter <SavelistAdapter.SavelistViewHolder> {

    private String[] mRoomPhotoUrlSet;
    private String[] mLocationSet;
    private int[] mRoomsSet;

    public SavelistAdapter(String[] mRoomPhotoUrlSet, String[] mLocationSet, int[] mRoomsSet) {
        this.mRoomPhotoUrlSet = mRoomPhotoUrlSet;
        this.mLocationSet = mLocationSet;
        this.mRoomsSet = mRoomsSet;
    }

    public static class SavelistViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView simpleDraweeView;
        private TextView tvLocation;
        private TextView tvRooms;

        public SavelistViewHolder(@NonNull View view) {
            super(view);
            this.simpleDraweeView = view.findViewById(R.id.sv_frag_savelist_room_photo);
            this.tvLocation = view.findViewById(R.id.tv_frag_savelist_location);
            this.tvRooms = view.findViewById(R.id.tv_frag_savelist_rooms);
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
        holder.simpleDraweeView.setImageURI(this.mRoomPhotoUrlSet[position]);
        holder.tvLocation.setText(this.mLocationSet[position]);
        holder.tvRooms.setText("숙소"+this.mRoomsSet[position]+"개");
    }

    @Override
    public int getItemCount() {
        return mRoomPhotoUrlSet.length;
    }


}
