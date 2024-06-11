package com.example.cdmuseum.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.db.UserManage;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * LoginActivity
 * 登录页面
 */
public class AdminLoginActivity extends BaseActivity {

    private UserManage userManage;
    @BindView(R.id.login_username)
    EditText ed_name;
    @BindView(R.id.login_pwd)
    EditText ed_pwd;

    @BindView(R.id.login_back_user)
    TextView login_back_user;

    @BindView(R.id.login_bnt)
    Button login_bnt;


    @OnClick({R.id.login_bnt, R.id.login_back_user})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_bnt: {
                String name = ed_name.getText().toString().trim();
                String pwd = ed_pwd.getText().toString().trim();
                if (name.equals("admin") && pwd.equals("123456")) {
                    startActivity(new Intent(AdminLoginActivity.this, ManagerActivity.class));
                    showToast("登录成功");
                } else {
                    showToast("账号密码错误");
                }


            }
            break;
            case R.id.login_back_user: {
                startActivity(new Intent(AdminLoginActivity.this, LoginActivity.class));
                finish();
            }
            break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_admin;
    }

    @Override
    protected void init() {
        initView();
    }


    private void initView() {

    }


}
