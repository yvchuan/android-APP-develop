package com.example.cdmuseum.ui.activity;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cdmuseum.R;
import com.example.cdmuseum.ui.adapter.MyFragmentAdapter;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.ui.fragment.HomeFragment;
import com.example.cdmuseum.ui.fragment.NewsFragment;
import com.example.cdmuseum.ui.fragment.ReserveFragment;
import com.example.cdmuseum.ui.fragment.BoutiqueFragment;
import com.example.cdmuseum.ui.fragment.UserFragment;
import com.example.cdmuseum.ui.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.main_pager)
    NoScrollViewPager mainpager;
    MyFragmentAdapter myFragmentAdapter;
    private long clickTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new BoutiqueFragment());
        fragments.add(new NewsFragment());
        fragments.add(new ReserveFragment());
        fragments.add(new UserFragment());
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);
        mainpager.setOffscreenPageLimit(4);
        mainpager.setAdapter(myFragmentAdapter);
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.tab_home:
                mainpager.setCurrentItem(0, false);
                break;
            case R.id.tab_store:
                mainpager.setCurrentItem(1, false);
                break;
            case R.id.tab_news:
                mainpager.setCurrentItem(2, false);
                break;
            case R.id.tab_rank:
                mainpager.setCurrentItem(3, false);
                break;
            case R.id.tab_me:
                mainpager.setCurrentItem(4, false);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            try {
                //正常退出
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}