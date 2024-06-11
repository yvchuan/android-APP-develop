package com.example.cdmuseum.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.bean.Qiye;
import com.example.cdmuseum.db.MedicDbutils;
import com.example.cdmuseum.ui.activity.DetailActivity;
import com.example.cdmuseum.base.LazyFragment;

import java.util.List;

import butterknife.BindView;

/**
 * ReserveFragment
 * 预约
 */
public class ReserveFragment extends LazyFragment {

    @BindView(R.id.gv)
    ListView gvShow;
    @BindView(R.id.tv_select)
    TextView tvselect;
    @BindView(R.id.login_et_zh)
    EditText etselect;
    Medicadapter medicadapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reserve;
    }

    @Override
    protected void loadData() {
        medicadapter = new Medicadapter(getActivity(), MedicDbutils.getInstance(getActivity()).load());
        gvShow.setAdapter(medicadapter);
        tvselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa = etselect.getText().toString();
                if (TextUtils.isEmpty(aa)) {
                    showToast("请检查搜索内容");
                    return;
                } else {
                    medicadapter = new Medicadapter(getActivity(), MedicDbutils.getInstance(getActivity()).loadByName(aa));
                    gvShow.setAdapter(medicadapter);
                }
            }
        });

    }


    class Medicadapter extends BaseAdapter {
        private Context context;
        private List<Qiye> listdata;

        public Medicadapter(Context context, List<Qiye> listdata) {
            this.context = context;
            this.listdata = listdata;
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
            Medicadapter.ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new Medicadapter.ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_memo, null);
                viewHolder.iv_pic = convertView.findViewById(R.id.ivPic);
                viewHolder.tv_text = convertView.findViewById(R.id.tvText);
                viewHolder.tv_price = convertView.findViewById(R.id.tv_price);
                viewHolder.ll = convertView.findViewById(R.id.ll);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (Medicadapter.ViewHolder) convertView.getTag();
            }
            viewHolder.tv_price.setText(listdata.get(position).getPrice());
            viewHolder.tv_text.setText(listdata.get(position).getCaiming());
            Glide.with(getActivity()).load(listdata.get(position).getPath()).into(viewHolder.iv_pic);
            viewHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("bean", listdata.get(position));
                    startActivity(intent);
                }
            });


            return convertView;
        }

        class ViewHolder {
            ImageView iv_pic;
            TextView tv_text, tv_price;
            LinearLayout ll;
        }
    }
}