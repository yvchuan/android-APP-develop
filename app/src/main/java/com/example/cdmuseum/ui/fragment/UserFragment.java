package com.example.cdmuseum.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Process;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.cdmuseum.R;
import com.example.cdmuseum.ui.activity.AboutActivity;
import com.example.cdmuseum.ui.activity.ReserveRecordsActivity;
import com.example.cdmuseum.ui.activity.ReserveActivity;
import com.example.cdmuseum.ui.activity.LoginActivity;
import com.example.cdmuseum.ui.activity.UserinfoActivity;
import com.example.cdmuseum.base.LazyFragment;

import butterknife.BindView;

public class UserFragment extends LazyFragment implements View.OnClickListener {
    @BindView(R.id.rl_userinfo)
    RelativeLayout rlUserinfo;

    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.rl_restart)
    RelativeLayout rlRestart;
    @BindView(R.id.rl_reserve)
    RelativeLayout rlgouwu;
    @BindView(R.id.rl_records)
    RelativeLayout rldingdan;
    @BindView(R.id.ll_exit)
    LinearLayout ll_exit;
    private SharedPreferences sp;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void loadData() {
        rlUserinfo.setOnClickListener(this);

        rlAbout.setOnClickListener(this);
        rlRestart.setOnClickListener(this);
        ll_exit.setOnClickListener(this);
        rlgouwu.setOnClickListener(this);
        rldingdan.setOnClickListener(this);
        sp = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_userinfo:
                Intent intent = new Intent(getActivity(), UserinfoActivity.class);
                startActivity(intent);
                break;

            case R.id.rl_about:
                Intent intent2 = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_restart:
                showDialog();
                break;
            case R.id.ll_exit:
                loginOut();
                break;
            case R.id.rl_reserve:
                Intent intent4 = new Intent(getActivity(), ReserveActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_records:
                Intent intent5 = new Intent(getActivity(), ReserveRecordsActivity.class);
                startActivity(intent5);
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("确定切换账号吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //取消自动登录
                        sp.edit().putBoolean("loginboll", false).commit();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    protected void loginOut() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("确定退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                try {
                    //正常退出
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}