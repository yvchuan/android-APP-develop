package com.example.cdmuseum.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.Peifang;
import com.example.cdmuseum.db.PeifangDbutils;

import java.util.ArrayList;
import java.util.List;

public class ReserveRecordsActivity extends BaseActivity {
    private RelativeLayout rlback;
    private ListView lv_reserve;
    Context context;
    Medicadapter medicadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reserve_records;
    }

    @Override
    protected void init() {
        rlback = findViewById(R.id.rl_back);
        lv_reserve = findViewById(R.id.lv_reserve);
        context = this;
        medicadapter = new Medicadapter(context, PeifangDbutils.getInstance(getApplicationContext()).load());
        lv_reserve.setAdapter(medicadapter);

        rlback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showAlertDialog(Peifang gouwu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("请选择操作")
                .setMessage("确定删除此订单吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "id" + gouwu.getId(), Toast.LENGTH_SHORT).show();
//                        YuyueDbUtils.getInstance(getApplicationContext()).delete(getApplicationContext(), gouwu.getId() + "");
                        PeifangDbutils.getInstance(getApplicationContext()).delete(getApplicationContext(), gouwu.getId() + "");
                        medicadapter = new Medicadapter(context, PeifangDbutils.getInstance(getApplicationContext()).load());
                        lv_reserve.setAdapter(medicadapter);
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        medicadapter = new Medicadapter(context, PeifangDbutils.getInstance(getApplicationContext()).load());
        lv_reserve.setAdapter(medicadapter);
    }

    class Medicadapter extends BaseAdapter {
        private Context context;
        private List<Peifang> listdata;

        public Medicadapter(Context context, List<Peifang> listdata) {
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
            Peifang gouwu = listdata.get(position);
            Log.e("app", "item = " + gouwu.toString());
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
            viewHolder.tv_price.setText(listdata.get(position).getChanpinprice());
            viewHolder.tv_text.setText(listdata.get(position).getChanpinname());
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
}