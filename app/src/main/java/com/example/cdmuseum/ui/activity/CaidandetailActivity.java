package com.example.cdmuseum.ui.activity;

import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.Qiye;

public class CaidandetailActivity extends BaseActivity {
    private ImageView iv_tupian;
    private EditText et_caiming;
    private EditText et_price;
    private EditText et_xiangqing;
    Qiye medic;
    String path;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_caidandetail;
    }

    @Override
    protected void init() {
        et_price = findViewById(R.id.et_price);
        et_caiming = findViewById(R.id.et_caiming);
        iv_tupian = findViewById(R.id.iv_tupian);
        et_xiangqing = findViewById(R.id.et_jieshao);
        medic = (Qiye) getIntent().getSerializableExtra("bean");
        et_price.setText(medic.getPrice());
        et_caiming.setText(medic.getCaiming());
        et_xiangqing.setText(medic.getJieshao());
        path = medic.getPath();
        Glide.with(this).load(path).into(iv_tupian);
    }
}