package com.shinplest.airbnbclone.src.main.fragment_savelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

public class FragSavelist extends Fragment {

    private RecyclerView mRvSavelist;

    public FragSavelist() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_savelist, container, false);

        mRvSavelist = view.findViewById(R.id.rv_frag_savelist);
        mRvSavelist.setHasFixedSize(true);
        mRvSavelist.setLayoutManager(new LinearLayoutManager(getActivity()));
        String[] roomPhotoUrlSet = {"https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG",
                "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG",
                "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG",
                "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG",
                "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG",
                "https://t4.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/76Cz/image/LVlkZ5olVzCMIaEb7DqwmmE1Nxc.JPG"};
        String[] locationSet = {"한국", "신주쿠, Shinjuku City, 일본", "한국", "신주쿠, Shinjuku City, 일본", "한국", "신주쿠, Shinjuku City, 일본"};
        int[] roomsSet = {1, 2, 1, 2, 1, 2};
        mRvSavelist.setAdapter(new SavelistAdapter(roomPhotoUrlSet, locationSet, roomsSet));

        return view;
    }
}
