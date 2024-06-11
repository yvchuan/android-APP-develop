package com.example.cdmuseum.ui.activity;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdmuseum.R;
import com.example.cdmuseum.utils.Constants;
import com.example.cdmuseum.base.BaseActivity;

import butterknife.BindView;

public class UserinfoActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_pwd)
    TextView tvPwd;
    @BindView(R.id.rl_back)
    RelativeLayout back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_userinfo;
    }

    @Override
    protected void init() {
        tvName.setText("用户名:" + Constants.passname);
        tvPwd.setText("用户密码:" + Constants.passpwd);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}