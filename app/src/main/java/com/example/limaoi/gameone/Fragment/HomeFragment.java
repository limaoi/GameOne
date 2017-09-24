package com.example.limaoi.gameone.Fragment;

import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.limaoi.gameone.R;
import com.example.limaoi.gameone.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limaoi on 2017/6/18.
 * E-mail：autismlm20@vip.qq.com
 */

public class HomeFragment extends BaseFragment {

    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private TabAdapter adapter;
    private List<String> mTitles;
    private Toolbar mHomeToolbar;
    public static final String[] tabTitle = new String[]{"英雄联盟", "王者荣耀", "绝地求生", "职业联赛", "主播糗事", "其他"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_home, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        MIUISetStatusBarLightMode(getActivity().getWindow(), true);

        mTableLayout = (TabLayout) view.findViewById(R.id.ty_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_viewpager);
        mHomeToolbar = (Toolbar) view.findViewById(R.id.id_nav_home_toolbar);

        mHomeToolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(mHomeToolbar);
        setHasOptionsMenu(true);


        mTitles = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            mTitles.add(tabTitle[i]);
        }

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            fragments.add(TabLayoutFragment.newInstance(i + 1));
        }
        adapter = new TabAdapter(this.getChildFragmentManager(), fragments, mTitles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(adapter);
        //将TabLayout和ViewPager关联
        mTableLayout.setupWithViewPager(mViewPager);
        //设置可以滑动
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        return view;
    }
}

