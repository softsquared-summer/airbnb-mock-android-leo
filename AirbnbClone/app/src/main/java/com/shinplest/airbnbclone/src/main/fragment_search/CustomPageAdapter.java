package com.shinplest.airbnbclone.src.main.fragment_search;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;

public class CustomPageAdapter extends PagerAdapter {

    Context context;
    String urls[];
    LayoutInflater layoutInflater;

    public CustomPageAdapter(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.viewpager_item, container, false);

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sv_house);
        simpleDraweeView.setImageURI(Uri.parse(urls[position]));

        container.addView(itemView);

        //listening to image click
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
