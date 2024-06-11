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
        mData.add(new BoutiqueGv("普宁肠粉", "普宁肠粉\n潮汕地区深受喜爱的小吃\n\n经过普宁人的改良\n\n加入了鸡蛋、青菜、香菇丝、鱿鱼、虾米等食材，并在酱汁上进行创新，使其色香味俱佳。这种肠粉不仅口感爽滑，而且选料和配料都非常严格，是普宁人早餐和夜宵的首选。搭配一杯清香的观音茶，更是美味满足。", R.drawable.changfen));
        //mData.add(new BoutiqueGv("普宁肠粉", "狩猎纹铜壶，战国\n成都青羊小区出土\n\n腹径26厘米，高41.4厘米\n\n壶口微侈，颈部瘦长，椭圆形腹，矮圈足。肩部两侧有对称的铺首衔环一对。从壶颈口部至圈足，全身分为七段，均有不同的图案和纹饰组成。主题图案有羽人仙鹤图、狩猎图、三足鸟向日图等，在三组图案之间，分别由几何菱形纹、蟠螭纹、云雷纹等纹饰间隔。", R.drawable.qin_1));

        mData.add(new BoutiqueGv("普宁油炸豆干", "普宁油炸豆干\n\n广东省揭阳市普宁地区的特色小吃\n\n以其外酥内嫩的口感和独特的豆香味而受到人们的喜爱\n\n这种豆干主要呈方块状，有黄、白两种颜色，其中黄皮豆干最为常见。炸好的豆干外皮酥脆，内里白嫩，因此有“外金内银”的称号。食用时，搭配上当地的酱料，味道更加鲜美，别有一番风味。普宁豆干是用大豆、薯粉、石膏、卤水等原料制成的豆制品，是潮汕地区的民俗小吃和特产之一。", R.drawable.dougan));
        //mData.add(new BoutiqueGv("蚕纹铜戈", "蚕纹铜戈，战国\n\n成都交通巷出土\n\n长25.2、宽6.3厘米\n\n援部两面均饰以兽面纹。内部正反两面均饰有蚕形图像，实证了先秦时期蜀人种桑养蚕、从事纺织的悠久历史。", R.drawable.qin_2));
        mData.add(new BoutiqueGv("普宁豆酱", "普宁豆酱\n\n广东省普宁市洪阳镇的传统豆制品\n\n拥有150多年的生产历史\n\n它主要由鲜庄黄豆、面粉和食盐等原料制成，经过发酵、晒制和蒸气杀菌等精细的生产流程。这种豆酱色泽金黄，味道香甜，富含蛋白质、氨基酸和还原糖，是烹饪中的绝佳调味品。普宁豆酱不仅在地方菜肴中有着广泛的应用，还因其出色的品质而成为馈赠亲友的珍品，甚至在海外华侨中也有着很高的知名度。", R.drawable.doujiang));
        //mData.add(new BoutiqueGv("漆豆", "漆豆，战国\n\n成都商业街船棺葬出土\n\n豆盘口径41.5、足径37.5，通高23.8厘米\n\n木胎。器表髹黑漆。盘面大部分涂朱，用线面结合的方法绘制复杂的纹样，盘外壁纹饰似蝉纹。圈足上则以朱、赭两色单线勾填蟠螭纹。", R.drawable.qin_3));
        mData.add(new BoutiqueGv("惠来绿豆饼", "惠来绿豆饼\n\n广东省揭阳市惠来县龙江镇的特色小吃\n\n这种饼的馅心冰甜，整体咬下后松软可口，绵软润滑，给人一种冰凉的感觉，因此也有“冰饼”的称号。制作绿豆饼主要选用优质面粉、绿豆粉、白砂糖、花生油、杏仁和芝麻等材料，使其香酥可口、细腻香软，深受当地人喜爱。", R.drawable.bing));
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