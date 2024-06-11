package com.example.cdmuseum.ui.activity;

import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsdetailsActivity extends BaseActivity {

    @BindView(R.id.webView_news_details)
    WebView mWebView;
    @BindView(R.id.tv_return_newsdetails)
    TextView tv_return;
    @BindView(R.id.tv_title3)
    TextView tv_title;


    @BindView(R.id.rl_back)
    RelativeLayout back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsdetails;
    }

    @Override
    protected void init() {
        initViews();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_return_newsdetails})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_return_newsdetails: {
                showToast("返回");
                finish();
            }
            break;
        }
    }


    private void initViews() {
        Intent intent = getIntent();

        String news_url = intent.getStringExtra("url");
        String news_title = intent.getStringExtra("title");

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);// 启用javascript

        //设置true,才能让Webivew支持<meta>标签的viewport属性
        mWebView.getSettings().setUseWideViewPort(true);
        //设置可以支持缩放
        //mWebView.getSettings().setSupportZoom(true);
        //设置出现缩放工具
        //mWebView.getSettings().setBuiltInZoomControls(true);
        //设定缩放控件隐藏
        //mWebView.getSettings().setDisplayZoomControls(false);

        //最小缩放等级
        // mWebView.setInitialScale(25);


        tv_title.setText(news_title);
        mWebView.loadUrl(news_url);


    }


}