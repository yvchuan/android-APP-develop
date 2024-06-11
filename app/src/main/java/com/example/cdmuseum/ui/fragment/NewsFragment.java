package com.example.cdmuseum.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cdmuseum.R;
import com.example.cdmuseum.ui.activity.NewsdetailsActivity;
import com.example.cdmuseum.base.LazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class NewsFragment extends LazyFragment {

    private ArrayAdapter<String> adapter;
    private List<String> list_news;
    private List<String> list_news_url;


//    @BindView(R.id.tv_return_news)
//    TextView tv_return_news;

    @BindView(R.id.listview_news)
    ListView listview_news;

//
//    @OnClick({R.id.tv_return_news})
//    public void onClick(View view) {
//
//        switch (view.getId()) {
//            case R.id.ll_intro: {
//                showToast("Hello");
////                startActivity(new Intent(getContext(), IntroActivity.class));
//            }
//            break;
//        }
//    }

    private void initNews() {
        list_news = new ArrayList<String>();
        list_news_url = new ArrayList<String>();

        list_news.add("•“普宁肠粉详细步骤及配料大全”");
        list_news_url.add("https://baijiahao.baidu.com/s?id=1610511618962547643&wfr=spider&for=pc&searchword=%E6%99%AE%E5%AE%81%E8%82%A0%E7%B2%89%E5%81%9A%E6%B3%95%E5%A4%A7%E5%85%A8");
        //https://www.cdmuseum.com/xinwen/202211/2899.html


        list_news.add("•“牛肉丸做法大全，快快点击进来学习吧");
        list_news_url.add("https://baijiahao.baidu.com/s?id=1610511618962547643&wfr=spider&for=pc&searchword=%E6%99%AE%E5%AE%81%E8%82%A0%E7%B2%89%E5%81%9A%E6%B3%95%E5%A4%A7%E5%85%A8");
        //https://www.cdmuseum.com/xinwen/202211/2898.html

        list_news.add("•做普宁豆干原来这么简单？");
        list_news_url.add("https://m.renrenshipu.com/caipu/video/15485.html#step1");
        //https://www.cdmuseum.com/xinwen/202211/2897.html

        list_news.add("•拥有潮汕同款蚝烙,你也可以学会");
        list_news_url.add("https://m.xiachufang.com/recipe/104549974/");
        //https://www.cdmuseum.com/xinwen/202211/2896.html

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void loadData() {
        initNews();
        adapter = new ArrayAdapter<String>(
                getContext(), android.R.layout.simple_expandable_list_item_1, list_news);
        listview_news.setAdapter(adapter);

        listview_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), NewsdetailsActivity.class);
                intent.putExtra("title", list_news.get(position));
                intent.putExtra("url", list_news_url.get(position));

                startActivity(intent);
            }
        });
    }


}