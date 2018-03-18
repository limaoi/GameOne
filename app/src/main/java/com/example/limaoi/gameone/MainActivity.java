package com.example.limaoi.gameone;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.limaoi.gameone.Fragment.CircleFragment;
import com.example.limaoi.gameone.Fragment.HomeFragment;
import com.example.limaoi.gameone.Fragment.MeFragment;
import com.example.limaoi.gameone.Fragment.VideoFragment;
import com.example.limaoi.gameone.adapter.TabPageAdapter;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by limaoi on 2017/6/17.
 * E-mail：autismlm20@vip.qq.com
 */

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private boolean isExit;
    private ViewPager mViewPager;
    private List<Fragment> mFragments = new ArrayList<>();
    private TabPageAdapter mTabPageAdapter;
    private RadioGroup mRadioGroup;
    private RadioButton homeRadioButton;
    private RadioButton videoRadioButton;
    private RadioButton circleRadioButton;
    private RadioButton meRadioButton;

    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    private static final int READ_PHONE_STATE = 2;
    private static final int ACCESS_FINE_LOCATION = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        //初始化控件
        initView();

        //初始化事件
        initEvents();
    }

    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        VideoFragment videoFragment = new VideoFragment();
        CircleFragment circleFragment = new CircleFragment();
        MeFragment meFragment = new MeFragment();
        mFragments.add(homeFragment);
        mFragments.add(videoFragment);
        mFragments.add(circleFragment);
        mFragments.add(meFragment);
    }

    private void initView() {
        mTabPageAdapter = new TabPageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        homeRadioButton = (RadioButton) findViewById(R.id.rb_home);
        videoRadioButton = (RadioButton) findViewById(R.id.rb_video);
        circleRadioButton = (RadioButton) findViewById(R.id.rb_circle);
        meRadioButton = (RadioButton) findViewById(R.id.rb_me);
    }

    private void initEvents() {
        MPermissions.requestPermissions(MainActivity.this, READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        mViewPager.setAdapter(mTabPageAdapter);
        mViewPager.addOnPageChangeListener(this);
        mTabPageAdapter.updateData(mFragments);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        selectPage(0);
                        break;
                    case R.id.rb_video:
                        selectPage(1);
                        break;
                    case R.id.rb_circle:
                        selectPage(2);
                        break;
                    case R.id.rb_me:
                        selectPage(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void selectPage(int position) {
        switch (position) {
            case 0:
                homeRadioButton.setChecked(true);
                break;
            case 1:
                videoRadioButton.setChecked(true);
                break;
            case 2:
                circleRadioButton.setChecked(true);
                break;
            case 3:
                meRadioButton.setChecked(true);
                break;
        }
        mViewPager.setCurrentItem(position, false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(READ_PHONE_STATE)
    public void requestSdcardSuccess() {
        MPermissions.requestPermissions(MainActivity.this, ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @PermissionDenied(READ_PHONE_STATE)
    public void requestSdcardFailed() {
        Toast.makeText(this, "拒绝授权，如需请自行授权!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mhandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                System.exit(0);
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
