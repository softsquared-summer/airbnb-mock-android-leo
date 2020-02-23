package com.shinplest.airbnbclone.src.main.fragment_message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shinplest.airbnbclone.R;

public class MessageFragment extends Fragment {

    private RecyclerView mRvMessage;

    public MessageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        //새로고침 옵션메뉴 보이게
        setHasOptionsMenu(true);


        mRvMessage = view.findViewById(R.id.frag_mess);
        mRvMessage.setHasFixedSize(true);
        mRvMessage.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvMessage.setAdapter();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.frag_message_menu, menu);

    }
}
