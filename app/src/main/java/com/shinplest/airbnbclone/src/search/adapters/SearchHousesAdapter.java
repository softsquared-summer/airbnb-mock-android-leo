package com.shinplest.airbnbclone.src.search.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.house.HouseActivity;
import com.shinplest.airbnbclone.src.search.interfaces.SearchActivityView;
import com.shinplest.airbnbclone.src.search.models.SimpleHouseInfoResponse;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.util.ArrayList;
import java.util.HashMap;

import static com.shinplest.airbnbclone.src.general.ApplicationClass.USER_NO;


public class SearchHousesAdapter extends RecyclerView.Adapter<SearchHousesAdapter.MyViewHolder> {
    private SearchActivityView mSearchActivityView;
    private Context context;
    private ArrayList<SimpleHouseInfoResponse.Result> mHouseDataList;

    //뷰페이져 상태저장하는 hashMap
    HashMap<Integer, Integer> mViewPagerState = new HashMap<>();

    public SearchHousesAdapter(Context context, ArrayList<SimpleHouseInfoResponse.Result> houseDataList, SearchActivityView searchActivityView) {
        this.context = context;
        this.mHouseDataList = houseDataList;
        this.mSearchActivityView = searchActivityView;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLlHouseContainer;

        private WormDotsIndicator mWormDotsIndicator;
        private ViewPager mVpHousePhotos;
        private TextView mTvHouseInfo, mTvStarAvg, mTvHouseName, mTvReviewCnt;
        private ImageView mIvSaveHouse;


        public MyViewHolder(@NonNull View view) {
            super(view);
            this.mLlHouseContainer = view.findViewById(R.id.ll_house_holder_container);
            this.mWormDotsIndicator = view.findViewById(R.id.dots_indicator);
            this.mVpHousePhotos = view.findViewById(R.id.vp_house_holder);
            this.mTvHouseInfo = view.findViewById(R.id.tv_house_holder_house_info);
            this.mTvStarAvg = view.findViewById(R.id.tv_house_holder_star_avg);
            this.mTvHouseName = view.findViewById(R.id.tv_house_holder_house_name);
            this.mTvReviewCnt = view.findViewById(R.id.tv_house_holder_review_count);
            this.mIvSaveHouse = view.findViewById(R.id.iv_house_holder_house_save);
        }
    }


    //여기서 각각의 뷰 홀더에 리스너를 달아주고,
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_house, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(holderView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final SimpleHouseInfoResponse.Result house = mHouseDataList.get(position);
        //하우스 넘버 받아오는 부분
        holder.mLlHouseContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int houseNo = house.getHouseNo();
                int isSave = house.getIsSave();
                Log.d("OnclickTest", "onClick: " + houseNo);
                //여기서 하우스 넘버로 새로운 액티비티로 넘어가줘야함
                Intent intent = new Intent(v.getContext(), HouseActivity.class);
                Bundle extras = new Bundle();
                extras.putInt("houseNo", houseNo);
                extras.putInt("isSave", isSave);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });

        //저장 결과에 따라서 이미지 바꿔줌
        if (house.getIsSave() == 0) {
            holder.mIvSaveHouse.setImageResource(R.drawable.search_save);
        } else
            holder.mIvSaveHouse.setImageResource(R.drawable.search_saved);

        holder.mIvSaveHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //저장안되어있으면
                if (house.getIsSave() == 0) {
                    //저장하고
                    mSearchActivityView.tryPostSaveHouse(USER_NO, house.getHouseNo());
                    holder.mIvSaveHouse.setImageResource(R.drawable.search_saved);
                    //저장됨으로 바꿔줌 클라도
                    house.setIsSave(1);
                } else {
                    mSearchActivityView.tryDeleteSavedHouse(USER_NO, house.getHouseNo());
                    holder.mIvSaveHouse.setImageResource(R.drawable.search_save);
                    //저정안됨으로바꿔줌 클라도
                    house.setIsSave(0);
                }

            }
        });


        //여기서 주소 받아와서 이미지 바꿔줌
        String[] url = house.getHouseImages().split(",");
        for (int i = 0; i < url.length; i++) {

            Log.d("testest", url[i]);
        }

        SearchHouseViewPageAdatper searchHouseViewPageAdatper = new SearchHouseViewPageAdatper(context, url);

        //뷰페이져 어댑터와 indicator 할당
        holder.mVpHousePhotos.setAdapter(searchHouseViewPageAdatper);
        holder.mWormDotsIndicator.setViewPager(holder.mVpHousePhotos);
        holder.mVpHousePhotos.setId(position + 1);

        if (mViewPagerState.containsKey(position)) {
            holder.mVpHousePhotos.setCurrentItem(mViewPagerState.get(position));
        }

        holder.mTvHouseInfo.setText(house.getHouseInfo());
        holder.mTvStarAvg.setText(house.getStarAvg());
        holder.mTvReviewCnt.setText("(" + house.getReviewCnt() + ")");
        holder.mTvHouseName.setText(house.getHouseName());
    }

    @Override
    public int getItemCount() {
        return mHouseDataList.size();
    }


}
