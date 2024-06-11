package com.example.cdmuseum.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import butterknife.BindView;

/**
 * BoutiqueHomeDetailActivity
 * 精品首页详情
 */
public class BoutiqueHomeDetailActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout back;


    @BindView(R.id.iv_img)
    ImageView iv_img;

    @BindView(R.id.tv_show_name)
    TextView tv_show_name;


    @BindView(R.id.tv_description)
    TextView tv_description;


    @BindView(R.id.tv_title)
    TextView tv_title;


    String show_name;
    String description;
    int img_id = 0;
    Intent intent;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_boutique_home_detail;
    }

    @Override
    protected void init() {
        intent = getIntent();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();


    }

    private void initView() {
        //设置网格布局
        show_name = intent.getStringExtra("show_name");
        description = intent.getStringExtra("description");
        img_id = intent.getIntExtra("img", 0);
        if (img_id != 0) {
            iv_img.setImageResource(img_id);
        } else {
            iv_img.setImageResource(R.drawable.qin_1);
        }
        tv_show_name.setText(show_name);
        tv_title.setText(show_name);
        tv_description.setText(description);
//        tv_description.setText(R.string.detail_1);
    }



}