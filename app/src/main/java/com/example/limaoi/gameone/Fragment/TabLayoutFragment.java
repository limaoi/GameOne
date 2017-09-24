package com.example.limaoi.gameone.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.limaoi.gameone.R;

/**
 * Created by limaoi on 2017/6/25.
 * E-mail：autismlm20@vip.qq.com
 */

public class TabLayoutFragment extends Fragment {

    public static String TABLAYOUT_FRAGMENT = "tab_fragment";
    private TextView txt;
    private int type;

    public static TabLayoutFragment newInstance(int type) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TABLAYOUT_FRAGMENT, type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TABLAYOUT_FRAGMENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tablayout,container,false);
        initViews(view);

        return view;
    }

    private void initViews(View view) {

        txt = (TextView) view.findViewById(R.id.tab_txt);


        switch (type) {
            case 1:
                txt.setText("这是英雄联盟Fragment");
                break;
            case 2:
                txt.setText("这是王者荣耀Fragment");
                break;
            case 3:
                txt.setText("这是绝地求生Fragment");
                break;
            case 4:
                txt.setText("这是职业联赛Fragment");
                break;
            case 5:
                txt.setText("这是主播糗事Fragment");
                break;
            case 6:
                txt.setText("这是其他Fragment");
                break;
        }
    }
}
