package com.example.cdmuseum.ui.fragment;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.ui.activity.IntroActivity;
import com.example.cdmuseum.base.LazyFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * HomeFragment
 * 首页
 */
public class HomeFragment extends LazyFragment implements OnBannerListener {

    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.ll_intro)
    LinearLayout ll_intro;

    @BindView(R.id.ll_open_time)
    LinearLayout ll_open_time;

    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;


    @OnClick({R.id.ll_intro, R.id.ll_open_time})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_intro: {
                showToast("图书馆简介");
                startActivity(new Intent(getContext(), IntroActivity.class));
            }
            break;
            case R.id.ll_open_time: {
                showToast("书籍推荐");
            }
            break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    /**
     * 轮播图的监听
     *
     * @param position
     */
    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
        //跳转
    }

    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.anhui_library_opentime);
        imagePath.add(R.drawable.anhui_library_banzhengxuzhi);
        imagePath.add(R.drawable.anhui_library_jieyueguize);
        imageTitle.add("开放时间");
        imageTitle.add("办证须知");
        imageTitle.add("借阅规则");
    }

    private void initViewBanner() {
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        banner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        banner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void loadData() {
        initData();
        initViewBanner();

    }


}