package com.shinplest.airbnbclone.src.main.fragment_search;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public GridItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = 100;

        //홀수번째 뷰면 오른쪽 여백
        if (parent.getChildLayoutPosition(view) % 2 == 0){
            outRect.right = 60;
        }
        else{
            outRect.right = 0;
        }
    }
}
