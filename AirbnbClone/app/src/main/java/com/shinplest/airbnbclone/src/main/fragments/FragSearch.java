package com.shinplest.airbnbclone.src.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;
import com.shinplest.airbnbclone.src.main.AdapterCard;

public class FragSearch extends BaseFragment {

    private LinearLayout mLlSearch;

    private RecyclerView mRvLookAround;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private SnapHelper snapHelper;

    public FragSearch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        //검색창 눌렀을때 온클릭 리스너
        mLlSearch = view.findViewById(R.id.ll_frag_search_search);
        mLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomToastFrag("test");
                //Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
            }
        });


        //모든 리사이클러에서 쓰일 스냅헬퍼
        snapHelper = new LinearSnapHelper();

        //첫번째 리사이클러
        mRvLookAround = view.findViewById(R.id.rv_frag_search_look_around);
        // 리사이클러뷰의 notify()처럼 데이터가 변했을 때 성능을 높일 때 사용한다.
        mRvLookAround.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRvLookAround.setLayoutManager(layoutManager);
        String[] textSet =  {"숙소","체험","어드벤처"};
        int[] imgSet = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};
        adapter = new AdapterCard(textSet,imgSet);
        mRvLookAround.setAdapter(adapter);
        snapHelper.attachToRecyclerView(mRvLookAround);



        return view;
    }
}
