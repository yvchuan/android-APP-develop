package com.example.cdmuseum.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.BoutiqueGv;
import com.example.cdmuseum.ui.adapter.BoutiqueHomeListAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

/**
 * BoutiqueHomeActivity
 * 精品首页
 */
public class BoutiqueHomeActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout back;


    @BindView(R.id.img_icon)
    ImageView img_icon;

    @BindView(R.id.tv_title)
    TextView tv_title;


    @BindView(R.id.tv_show_list)
    TextView tv_show_list;

    private List<BoutiqueGv> mData = null;
    private BoutiqueHomeListAdapter mAdapter = null;
    //
    @BindView(R.id.gv_data)
    GridView list_animal;


    String title;

    int img_id = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_boutique_home;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();

        title = intent.getStringExtra("title");
        img_id = intent.getIntExtra("index", 0);
        if (img_id != 0) {
            img_icon.setImageResource(img_id);
        } else {
            img_icon.setImageResource(R.drawable.boutique_1);
        }
        tv_title.setText(title);
        tv_show_list.setText(title + "列表");
    }

    public void initData() {
        mData = new LinkedList<BoutiqueGv>();
        mData.add(new BoutiqueGv("入馆须知", "1.请着装整洁,言谈举止文明。\n2.馆区内设有存包柜，随身携带的书包及其他物品可免费寄存，贵重物品随身携带。\n\n3.阅览室内请保持安静，移动通讯设备请置于静音状态。\n\n4.楼区内严禁烟火，请勿在馆区吸烟，勿携带易燃、易爆品入馆。\n5.使用安徽省图书馆无线网络时，应自觉遵守国家有关法律和法规，遵守网络礼仪和道德规范。不得从事危害公共安全、损害公众利益、侵害他人正当权益、窃取或泄露他人秘密以及散布、传播其他有害信息的活动。每个账号同时只能1个移动设备登录在线。\n", R.drawable.anhui_library_icon));
        //mData.add(new BoutiqueGv("普宁肠粉", "狩猎纹铜壶，战国\n成都青羊小区出土\n\n腹径26厘米，高41.4厘米\n\n壶口微侈，颈部瘦长，椭圆形腹，矮圈足。肩部两侧有对称的铺首衔环一对。从壶颈口部至圈足，全身分为七段，均有不同的图案和纹饰组成。主题图案有羽人仙鹤图、狩猎图、三足鸟向日图等，在三组图案之间，分别由几何菱形纹、蟠螭纹、云雷纹等纹饰间隔。", R.drawable.qin_1));

        mData.add(new BoutiqueGv("办证指南", "1.读者持有效证件(本人身份证、户口簿、老年证、残疾证、护照等)，填写“安徽省图书馆读者登记卡”，选择相应借阅证种类办理。亲子阅览证由监护人持本人身份证、幼儿户口簿或身份证办理。\n\n2.支付宝芝麻信用积分达到550分及以上，且经该平台认定综合信用良好者，可免押金办理支付宝信用证。\n\n3.持安徽省社保卡，通过自助终端设备现场开通借阅服务后可免押金借阅图书，也可通过社保卡属地服务平台或皖事通政务地图平台在线开通借阅服务。\n\n4.借阅证初始密码为持证人出生年月日八位数（如：20151208），读者可登陆安徽省图书馆网站“我的图书馆”栏目修改密码。支付宝信用证初始密码在支付宝注册页面自行设置。", R.drawable.anhui_banzhengxuzhi));
        //mData.add(new BoutiqueGv("蚕纹铜戈", "蚕纹铜戈，战国\n\n成都交通巷出土\n\n长25.2、宽6.3厘米\n\n援部两面均饰以兽面纹。内部正反两面均饰有蚕形图像，实证了先秦时期蜀人种桑养蚕、从事纺织的悠久历史。", R.drawable.qin_2));
        mData.add(new BoutiqueGv("自助服务", "1.借书流程：将图书放入设备的指定区域→点击屏幕中的“借书”→请选择借书的方式→①点击“支付宝”→使用支付宝扫描屏幕中生成的二维码→确认信息→借书完成；②点击“微信”→使用微信扫描屏幕中生成的二维码→确认信息→借书完成③点击“读者证”→将本人读者证放到设备的指定区域→确认信息→借书完成；④点击“社保卡”→将本人社保卡放到设备的指定区域→确认信息→借书完成；⑤点击“电子社保卡”→打开电子社保卡二维码扫码登录→确认信息→借书完成。\n\n2.还书流程：将图书放入设备的指定区域→点击屏幕中的“还书”→确认信息→还书完成。\n\n3.查询流程：点击屏幕中的“查询”→请选择查询的方式→①点击“支付宝”→使用支付宝扫描屏幕中生成的二维码→确认信息→查询完成②点击“微信”→使用微信扫描屏幕中生成的二维码→确认信息→查询完成③点击“读者证”→将本人读者证放到设备的指定区域→确认信息→查询完成。④点击“社保卡”→将本人社保卡放到设备的指定区域→确认信息→查询完成；⑤点击“电子社保卡”→打开电子社保卡二维码扫码登录→确认信息→查询完成。", R.drawable.anhui_zizhufuwu));
        //mData.add(new BoutiqueGv("漆豆", "漆豆，战国\n\n成都商业街船棺葬出土\n\n豆盘口径41.5、足径37.5，通高23.8厘米\n\n木胎。器表髹黑漆。盘面大部分涂朱，用线面结合的方法绘制复杂的纹样，盘外壁纹饰似蝉纹。圈足上则以朱、赭两色单线勾填蟠螭纹。", R.drawable.qin_3));
        mData.add(new BoutiqueGv("读者意见信箱", "尊敬的读者：\n\n您好！感谢您对安徽省图书馆的关心和支持。欢迎您提出宝贵意见和建议，帮助我们改进和完善读者服务。\n\n我们的读者意见邮箱是stsgbgs@163.com。", R.drawable.anhui_duzhexinxiang));
        //mData.add(new BoutiqueGv("西周兽头双耳铜罍", "西周兽头双耳铜罍\n\n1978年2月21日成都南郊工地出土\n\n口径38.7、底径34.8、腹径55.6、最宽76.4、高66.3厘米", R.drawable.qin_4));
        mAdapter = new BoutiqueHomeListAdapter((LinkedList<BoutiqueGv>) mData, this);
        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "点击" + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BoutiqueHomeActivity.this, BoutiqueHomeDetailActivity.class);
                intent.putExtra("img", mData.get(i).getaIcon());
                intent.putExtra("show_name", mData.get(i).getaName());
                intent.putExtra("description", mData.get(i).getaSpeak());
                intent.putExtra("index", i);
                startActivity(intent);
            }
        });
    }


}