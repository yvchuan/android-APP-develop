package com.example.cdmuseum.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.User;
import com.example.cdmuseum.db.UserManage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * LoginActivity
 * 登录页面
 */
public class LoginActivity extends BaseActivity {

    private UserManage userManage;
    private EditText ed_name, ed_pwd;
    private Button login_bnt;
    private TextView reg;
    private CheckBox chx1, chx2;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    @BindView(R.id.login_admin)
    TextView login_admin;

    //    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.login_box1:
//                //记住密码
//                String userstr = ed_name.getText().toString();
//                String pwdstr = ed_pwd.getText().toString();
//                boolean Chxes = chx1.isChecked();
//                if (Chxes) {
//                    editor = sp.edit();
//                    editor.putString("username", userstr);
//                    editor.putString("userpwd", pwdstr);
//                    editor.putBoolean("chxbool", true);
//                    editor.commit();
//                } else {
//                    editor = sp.edit();
//                    editor.putString("username", null);
//                    editor.putString("userpwd", null);
//                    editor.putBoolean("chxbool", false);
//                    editor.commit();
//                }
//                break;
//            case R.id.login_box2:
//                if (chx2.isChecked()) {
//                    //确定自动登录
//                    sp.edit().putBoolean("loginboll", true).commit();
//                } else {
//                    //取消自动登录
//                    sp.edit().putBoolean("loginboll", false).commit();
//                }
//                break;
//            default:
//                break;
//        }
//    }
    @OnClick({R.id.login_admin, R.id.login_reg, R.id.login_box1, R.id.login_box2})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_admin: {
                showToast("工作人员登录");
                startActivity(new Intent(LoginActivity.this, AdminLoginActivity.class));
            }
            break;
            case R.id.login_reg: {
                showToast("美食推荐");
            }
            break;
            case R.id.login_box1:
                //记住密码
                String userstr = ed_name.getText().toString();
                String pwdstr = ed_pwd.getText().toString();
                boolean Chxes = chx1.isChecked();
                if (Chxes) {
                    editor = sp.edit();
                    editor.putString("username", userstr);
                    editor.putString("userpwd", pwdstr);
                    editor.putBoolean("chxbool", true);
                    editor.commit();
                } else {
                    editor = sp.edit();
                    editor.putString("username", null);
                    editor.putString("userpwd", null);
                    editor.putBoolean("chxbool", false);
                    editor.commit();
                }
                break;
            case R.id.login_box2:
                if (chx2.isChecked()) {
                    //确定自动登录
                    sp.edit().putBoolean("loginboll", true).commit();
                } else {
                    //取消自动登录
                    sp.edit().putBoolean("loginboll", false).commit();
                }
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        userManage = new UserManage(this);
        //Context.MODE_PRIVATE：指定该SharedPreferences数据只能被本应用程序读、写
        sp = this.getSharedPreferences("user", Context.MODE_PRIVATE);
        initView();
    }


    private void initView() {
        //初始化控件
        ed_name = findViewById(R.id.login_username);
        ed_pwd = findViewById(R.id.login_pwd);
        login_bnt = findViewById(R.id.login_bnt);
        reg = findViewById(R.id.login_reg);
        chx1 = findViewById(R.id.login_box1);
        chx2 = findViewById(R.id.login_box2);
//        chx1.setOnClickListener(this);
//        chx2.setOnClickListener(this);
        //记住密码
        if (sp.getBoolean("chxbool", false)) {
            ed_name.setText(sp.getString("username", null));
            ed_pwd.setText(sp.getString("userpwd", null));
            chx1.setChecked(true);
        }
        //自动登录
        if (sp.getBoolean("loginboll", false)) {
            chx2.setChecked(true);
            handler.sendEmptyMessage(3);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            switch (message.what) {
                case 1:
                    Toast.makeText(getBaseContext(), "您输入的内容有空!", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getBaseContext(), "用户名或密码错误!", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getBaseContext(), "欢迎登陆!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        login_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strname = ed_name.getText().toString();
                String strpwd = ed_pwd.getText().toString();
                if (!TextUtils.isEmpty(strname) || !TextUtils.isEmpty(strpwd)) {
                    //将数据内容封装为list集合
                    ArrayList<User> list = userManage.getAll();
                    System.out.println("数据的长度是" + list.size());
                    boolean sql = false;
                    //遍历数据库
                    for (int i = 0; i < list.size(); i++) {
                        if (strname.equals(list.get(i).getUsername()) && strpwd.equals(list.get(i).getUserpwd())) {
                            sql = true;
                        } else {
                            handler.sendEmptyMessage(2);
                            sql = false;
                        }
                    }
                    //反馈登录情况
                    if (sql) {
                        handler.sendEmptyMessage(3);
                    }

                } else {
                    handler.sendEmptyMessage(1);
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


}
