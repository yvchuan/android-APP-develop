package com.example.cdmuseum.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cdmuseum.R;
import com.example.cdmuseum.bean.BoutiqueLv;
import com.example.cdmuseum.base.LazyFragment;
import com.example.cdmuseum.ui.activity.BoutiqueHomeActivity;
import com.example.cdmuseum.ui.adapter.BoutiqueFragmentAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * StoreFragment
 * 商城代码
 */
public class BoutiqueFragment extends LazyFragment {

    private List<BoutiqueLv> mData = null;
    //    private Context mContext;
    private BoutiqueFragmentAdapter mAdapter = null;
    //
    @BindView(R.id.lv_data)
    ListView list_animal;

//    @BindView(R.id.tv_select)
//    TextView tvselect;
//
//    @BindView(R.id.login_et_zh)
//    EditText etselect;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void loadData() {
        initData();


    }

    public void initData() {
        mData = new LinkedList<BoutiqueLv>();
        mData.add(new BoutiqueLv("服务指南", "服务指南", R.drawable.anhui_fuwuzhinan));
//        mData.add(new BoutiqueLv("普宁肠粉", "普宁肠粉", R.drawable.boutique_1));
        mData.add(new BoutiqueLv("数字资源", "数字资源", R.drawable.anhui_shuziziyuan));
//        mData.add(new BoutiqueLv("两汉精品展示", "两汉精品展示", R.drawable.boutique_2));
        mData.add(new BoutiqueLv("志愿者服务", "志愿者服务", R.drawable.anhui_zhiyuanzhefuwu));
//        mData.add(new BoutiqueLv("唐宋精品展示", "唐宋精品展示", R.drawable.boutique_3));
//        mData.add(new BoutiqueLv("惠来美食", "惠来美食", R.drawable.huilai));
////        mData.add(new BoutiqueLv("", "明清精品展示", R.drawable.boutique_4));
//        mData.add(new BoutiqueLv("洪阳美食", "洪阳美食", R.drawable.hongyang));
//        mData.add(new BoutiqueLv("近代精品展示", "近代精品展示", R.drawable.boutique_5));
        mAdapter = new BoutiqueFragmentAdapter((LinkedList<BoutiqueLv>) mData, getContext());
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "点击" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BoutiqueHomeActivity.class);
                intent.putExtra("index", mData.get(i).getaIcon());
                intent.putExtra("title", mData.get(i).getaName());
                startActivity(intent);
            }
        });
    }


}