package com.shinplest.airbnbclone.src.main.fragment_message;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    private ArrayList<Message> message;



        public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeView;
        private TextView tvUserName, tvDate, tvContent,tvRoom,  tvAvailable;

        public MyViewHolder(View view) {
            super(view);
            this.simpleDraweeView = view.findViewById(R.id.sv_frag_message_holder_profile);
            this.tvUserName = view.findViewById(R.id.tv_frag_message_holder_username;
            this.tvDate = view.findViewById(R.id.tv_frag_message_holder_date);
            this.tvContent = view.findViewById(R.id.tv_frag_message_holder_content);
            this.tvRoom = view.findViewById(R.id.tv_frag_message_holder_room);
            this.tvAvailable = view.findViewById(R.id.tv_frag_message_holder_avilable);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_message, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(Uri.parse(profileUrlSet.get(position)));
        holder.tvUserName.setText(userNameSet.get(position));
        holder.tvDate.setText(dateSet.get(position));
        holder.tvRoom.setText(roomSet.get(position));

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
