package com.shinplest.airbnbclone.src.main;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

import java.util.List;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> data;

    public ExpandableListAdapter(List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View headerView = null;
        View itemView = null;
        switch (type) {
            case HEADER:
                LayoutInflater headerInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                headerView = headerInflater.inflate(R.layout.expandable_list_header_frag_profile, parent, false);
                ListHeaderViewHolder headerHolder = new ListHeaderViewHolder(headerView);
                return headerHolder;
            case CHILD:
                LayoutInflater itemInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = itemInflater.inflate(R.layout.expandable_list_item_frag_profile, parent, false);
                ListItemViewHolder itemHolder = new ListItemViewHolder(itemView);
                return itemHolder;
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder headerController = (ListHeaderViewHolder) holder;
                headerController.refferalItem = item;
                headerController.header_title.setText(item.text);

                break;
            case CHILD:
                final ListItemViewHolder itemController = (ListItemViewHolder) holder;
                itemController.tvItem.setText(data.get(position).text);
                itemController.ivItem.setImageDrawable(data.get(position).image);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_title;
        public Item refferalItem;

        //헤더부분 뷰홀더
        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            header_title = (TextView) itemView.findViewById(R.id.header_title);
        }
    }

    private static class ListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItem;
        public ImageView ivItem;

        //헤더부분 뷰홀더
        public ListItemViewHolder(View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tv_expandable_item);
            ivItem = itemView.findViewById(R.id.iv_expandable_item);
        }
    }


    public static class Item {
        public int type;
        public String text;
        public Drawable image;

        public Item() {
        }

        public Item(int type, String text) {
            this.type = type;
            this.text = text;
        }

        public Item(int type, String text, Drawable image) {
            this.type = type;
            this.text = text;
            this.image = image;
        }
    }
}
