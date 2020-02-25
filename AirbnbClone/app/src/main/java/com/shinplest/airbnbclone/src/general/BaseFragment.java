package com.shinplest.airbnbclone.src.general;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.shinplest.airbnbclone.R;

public class BaseFragment extends Fragment {

    public ProgressDialog mProgressDialog;


    public void showCustomToastFrag(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
