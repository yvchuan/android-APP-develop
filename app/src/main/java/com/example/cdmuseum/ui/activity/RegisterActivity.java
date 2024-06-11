package com.example.cdmuseum.ui.activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.db.UserManage;

public class RegisterActivity extends BaseActivity {
    private EditText ed_name, ed_pwd;
    private Button bnt;
    private ImageView img;
    private UserManage manage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init() {
        manage = new UserManage(this);
        initView();
    }

    private void initView() {
        img = findViewById(R.id.reg_img_back);
        ed_name = findViewById(R.id.reg_username);
        ed_pwd = findViewById(R.id.reg_pwd);
        bnt = findViewById(R.id.reg_bnt);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1:
                    Toast.makeText(getBaseContext(), "您输入的内容有空！", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getBaseContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = ed_name.getText().toString();
                String strpwd = ed_pwd.getText().toString();
                if (!TextUtils.isEmpty(strname) || !TextUtils.isEmpty(strpwd)) {
                    manage.add(strname, strpwd);
                    handler.sendEmptyMessage(2);
                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }


}
