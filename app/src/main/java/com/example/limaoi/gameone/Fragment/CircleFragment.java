package com.example.limaoi.gameone.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.limaoi.gameone.R;

/**
 * Created by limaoi on 2017/6/18.
 * E-mail：autismlm20@vip.qq.com
 */

public class CircleFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MIUISetStatusBarLightMode(getActivity().getWindow(), true);

        return view;
    }
}