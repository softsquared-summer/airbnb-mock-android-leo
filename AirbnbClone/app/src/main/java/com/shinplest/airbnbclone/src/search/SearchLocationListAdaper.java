package com.shinplest.airbnbclone.src.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shinplest.airbnbclone.R;
import com.shinplest.airbnbclone.src.search.models.ExistLocationResponse;

import java.util.ArrayList;

public class SearchLocationListAdaper extends BaseAdapter {

    ArrayList<ExistLocationResponse.Result> mExistLoctionList;
    LayoutInflater mLayoutInflator;

    public SearchLocationListAdaper(ArrayList<ExistLocationResponse.Result> mExistLoctionList, Context context) {
        this.mExistLoctionList = mExistLoctionList;
        mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mExistLoctionList.size();
    }

    @Override
    public Object getItem(int position) {
        return mExistLoctionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String location = mExistLoctionList.get(position).getExistLocation();
        convertView = mLayoutInflator.inflate(R.layout.search_item, parent, false);
        TextView tvLocation = convertView.findViewById(R.id.tv_search_list_location);
        tvLocation.setText(location);

        return convertView;

    }


}
