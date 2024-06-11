package com.example.cdmuseum.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.Qiye;
import com.example.cdmuseum.db.MedicDbutils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {
    @BindView(R.id.gv)
    ListView gvShow;
    Medicadapter medicadapter;
    @BindView(R.id.iv_add)
    ImageView iv_add;
    @BindView(R.id.tv_select)
    TextView tvselect;
    @BindView(R.id.login_et_zh)
    EditText etselect;
    private Context context;


    @BindView(R.id.rl_back)
    RelativeLayout back;


    @BindView(R.id.rl_menu)
    RelativeLayout rl_menu;

    @OnClick({R.id.rl_menu})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_menu: {
                showToast("退出管理系统");
                startActivity(new Intent(ManagerActivity.this, LoginActivity.class));
            }
            break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    protected void init() {

        context = this;

        List<Qiye> load = MedicDbutils.getInstance(getApplicationContext()).load();
        medicadapter = new Medicadapter(getApplicationContext(), load);
        gvShow.setAdapter(medicadapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddcaidanActivity.class);
                startActivityForResult(intent, 101);
            }
        });
        tvselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa = etselect.getText().toString();
                if (TextUtils.isEmpty(aa)) {
                    showToast("请检查搜索内容");
                    return;
                } else {
                    medicadapter = new Medicadapter(getApplicationContext(), MedicDbutils.getInstance(getApplicationContext()).loadByName(aa));
                    gvShow.setAdapter(medicadapter);
                }
            }
        });
    }


    private void showAlertDialog(Qiye medic) {
        String[] items = {"查看详情", "修改", "删除"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择操作")
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = items[which];
                        switch (str) {
                            case "查看详情":
                                Intent intent = new Intent(getApplicationContext(), CaidandetailActivity.class);
                                intent.putExtra("bean", medic);
                                startActivity(intent);

                                break;
                            case "修改":
                                Intent intent1 = new Intent(getApplicationContext(), ChangeActivity.class);
                                intent1.putExtra("bean", medic);
                                startActivityForResult(intent1, 102);
                                break;
                            case "删除":
                                MedicDbutils.getInstance(getApplicationContext()).delete(getApplicationContext(), medic.getId() + "");
                                medicadapter = new Medicadapter(getApplicationContext(), MedicDbutils.getInstance(getApplicationContext()).load());
                                gvShow.setAdapter(medicadapter);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        medicadapter = new Medicadapter(getApplicationContext(), MedicDbutils.getInstance(getApplicationContext()).load());
        gvShow.setAdapter(medicadapter);
    }

    class Medicadapter extends BaseAdapter {
        private Context context;
        private List<Qiye> listdata;

        public Medicadapter(Context context, List<Qiye> listdata) {
            this.context = context;
            this.listdata = listdata;
            if (this.listdata == null) {
                this.listdata = new ArrayList<>();
            }
        }

        @Override
        public int getCount() {
            return listdata.size();
        }

        @Override
        public Object getItem(int position) {
            return listdata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Qiye medic = listdata.get(position);
            Log.e("app", "item = " + medic.toString());

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_memo, null);
                viewHolder.iv_pic = convertView.findViewById(R.id.ivPic);
                viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
                viewHolder.tv_text = convertView.findViewById(R.id.tvText);
                viewHolder.ll = convertView.findViewById(R.id.ll);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_price.setText(listdata.get(position).getPrice());
            viewHolder.tv_text.setText(listdata.get(position).getCaiming());
            Glide.with(context).load(listdata.get(position).getPath()).into(viewHolder.iv_pic);
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialog(listdata.get(position));
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        ImageView iv_pic;
        TextView tv_text, tv_price;
        LinearLayout ll;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_add_course, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                logout();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void logout() {
        startActivity(new Intent(ManagerActivity.this, LoginActivity.class));
        finish();
    }
}