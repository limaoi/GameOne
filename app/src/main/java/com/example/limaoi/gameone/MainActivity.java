package com.example.limaoi.gameone;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.limaoi.gameone.Fragment.CircleFragment;
import com.example.limaoi.gameone.Fragment.HomeFragment;
import com.example.limaoi.gameone.Fragment.MeFragment;
import com.example.limaoi.gameone.Fragment.VideoFragment;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;


/**
 * Created by limaoi on 2017/6/17.
 * E-mail：autismlm20@vip.qq.com
 */

public class MainActivity extends BaseActivity {

    private boolean isExit;


    private RadioGroup mRadioGroup;
    private HomeFragment mHomeFragment;
    private VideoFragment mVideoFragment;
    private CircleFragment mCircleFragment;
    private MeFragment mMeFragment;
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

        //初始化控件
        initView();

        //初始化事件
        initEvents();
    }

    private void initView() {


        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }

    private void initEvents() {


        MPermissions.requestPermissions(MainActivity.this, READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);

        setDefaultFragment(); //设置默认Fragment

        //底部导航栏
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.rb_home:
                        if (mHomeFragment == null) {
                            mHomeFragment = new HomeFragment();
                        }
                        transaction.replace(R.id.fragment_content, mHomeFragment);
                        break;
                    case R.id.rb_video:
                        if (mVideoFragment == null) {
                            mVideoFragment = new VideoFragment();
                        }
                        transaction.replace(R.id.fragment_content, mVideoFragment);
                        break;
                    case R.id.rb_circle:
                        if (mCircleFragment == null) {
                            mCircleFragment = new CircleFragment();
                        }
                        transaction.replace(R.id.fragment_content, mCircleFragment);
                        break;
                    case R.id.rb_me:
                        if (mMeFragment == null) {
                            mMeFragment = new MeFragment();
                        }
                        transaction.replace(R.id.fragment_content, mMeFragment);
                        break;
                    default:
                        break;
                }
                transaction.commit();
            }
        });
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        transaction.replace(R.id.fragment_content,
                mHomeFragment);
        transaction.commit();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(READ_PHONE_STATE)
    public void requestSdcardSuccess()
    {
        MPermissions.requestPermissions(MainActivity.this, ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @PermissionDenied(READ_PHONE_STATE)
    public void requestSdcardFailed()
    {
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
