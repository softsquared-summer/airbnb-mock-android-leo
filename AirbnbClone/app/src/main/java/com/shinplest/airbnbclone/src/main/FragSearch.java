package com.shinplest.airbnbclone.src.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.BaseFragment;

public class FragSearch extends BaseFragment {

    LinearLayout mLlSearch;

    public FragSearch() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//
//        mLlSearch = view.findViewById(R.id.ll_frag_search_search);
//        mLlSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showCustomToastFrag("test");
//                //Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
}
