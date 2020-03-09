package com.shinplest.airbnbclone.src.search.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.shinplest.airbnbclone.R;

public class SearchHouseViewPageAdatper extends PagerAdapter {

    Context context;
    String urls[];
    LayoutInflater layoutInflater;

    public SearchHouseViewPageAdatper(Context context, String[] urls) {
        this.context = context;
        this.urls = urls;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return urls.length ;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.house_viewpager_item, container, false);

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sv_house);
        simpleDraweeView.setImageURI(Uri.parse(urls[position]));

        container.addView(itemView);

        //이미지 클릭했을때 아래를 눌러달라고 요청 이거 해결법 고민중
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "아래의 숙소 이름을 클릭해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
