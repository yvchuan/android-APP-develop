package com.example.cdmuseum.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdmuseum.R;
import com.example.cdmuseum.utils.Constants;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.db.SqliteDBUtils;

import butterknife.BindView;

public class ChangepwdActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_again)
    EditText etPwdAgain;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_changepwd;
    }

    @Override
    protected void init() {
        Constants.ID = SqliteDBUtils.getInstance(getApplicationContext()).selectId(Constants.passname, Constants.passpwd);
        rlBack.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_next_step) {
            String pwd1 = etPwd.getText().toString();
            String pwd2 = etPwdAgain.getText().toString();
            if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
                showToast("输入密码不能为空");
                return;
            } else if (!pwd1.equals(pwd2)) {
                showToast("两次输入内容不一致");
                return;
            } else {
                Constants.passpwd = pwd1;
                SqliteDBUtils.getInstance(getApplicationContext()).change(getApplicationContext(), Constants.ID, Constants.passname, pwd1);
                showToast("密码修改成功");
                finish();
            }
        }
        if (v.getId() == R.id.rl_back) {
            finish();
        }
    }
}