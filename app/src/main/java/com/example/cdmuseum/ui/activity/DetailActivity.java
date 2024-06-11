package com.example.cdmuseum.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.Peifang;
import com.example.cdmuseum.bean.Shoucang;
import com.example.cdmuseum.bean.Qiye;
import com.example.cdmuseum.db.PeifangDbutils;
import com.example.cdmuseum.db.YuyueDbUtils;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_weizhi)
    TextView tvweizhi;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login1)
    TextView tvLogin1;
    @BindView(R.id.tv_xiangqing)
    TextView tvxiangqing;
    String path;
    Qiye medic;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    protected void init() {
        medic = (Qiye) getIntent().getSerializableExtra("bean");
        tvName.setText("商品名称:" + medic.getCaiming());
        tvweizhi.setText("商品价格:" + medic.getPrice());
        tvxiangqing.setText("商品介绍:" + medic.getJieshao());
        path = medic.getPath();
        Glide.with(getApplicationContext()).load(path).into(iv);


        Shoucang gouwu = new Shoucang();
        gouwu.setChanpinname(medic.getCaiming());
        gouwu.setChanpinprice(medic.getPrice());
        gouwu.setXiangqing(medic.getJieshao());
        path = medic.getPath();
        gouwu.setPath(path);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = YuyueDbUtils.getInstance(getApplicationContext()).insert(gouwu);
                if (i == 0) {
                    showToast("加入购车成功");
                    return;
                } else {
                    showToast("加入购车失败");
                }
            }
        });
        Peifang peifang = new Peifang();
        peifang.setChanpinname(medic.getCaiming());
        peifang.setChanpinprice(medic.getPrice());
        peifang.setXiangqing(medic.getJieshao());
        path = medic.getPath();
        peifang.setPath(path);
        tvLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = PeifangDbutils.getInstance(getApplicationContext()).insert(peifang);
                if (i == 0) {
                    showToast("购买成功");
                    return;
                } else {
                    showToast("购买失败");
                }
            }
        });
    }

}