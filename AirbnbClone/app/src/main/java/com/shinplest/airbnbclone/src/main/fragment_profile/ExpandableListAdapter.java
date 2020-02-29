package com.shinplest.airbnbclone.src.main.fragment_profile;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

import java.util.List;

import android.os.Handler;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;

public class ExpandableListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> data;
    private Context context;

    public ExpandableListAdapter(List<Item> data, Context context) {
        this.context = context;
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
                return new ListHeaderViewHolder(headerView);
            case CHILD:
                LayoutInflater itemInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                itemView = itemInflater.inflate(R.layout.expandable_list_item_frag_profile, parent, false);
                return new ListItemViewHolder(itemView);
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

            //로그아웃 기능
            case CHILD:
                final ListItemViewHolder itemController = (ListItemViewHolder) holder;
                if (position != 14)
                    ((ListItemViewHolder) holder).tvItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "구현되지 않은 기능입니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                else {
                    ((ListItemViewHolder) holder).tvItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "로그아웃 되었습니다.\n 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    USER_NO = 1;
                                    ((Activity) context).finishAffinity();
                                }
                            }, 1000);
                        }
                    });
                }
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
