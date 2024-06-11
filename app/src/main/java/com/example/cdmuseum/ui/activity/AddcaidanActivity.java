package com.example.cdmuseum.ui.activity;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cdmuseum.R;
import com.example.cdmuseum.base.BaseActivity;
import com.example.cdmuseum.bean.Qiye;
import com.example.cdmuseum.db.MedicDbutils;
import com.wildma.pictureselector.PictureBean;
import com.wildma.pictureselector.PictureSelector;

public class AddcaidanActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_tupian;
    private EditText et_caiming;
    private EditText et_price;
    private EditText et_xiangqing;
    String path;
    private TextView commit1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_addcaidan;
    }

    @Override
    protected void init() {
        et_price = findViewById(R.id.et_price);
        et_caiming = findViewById(R.id.et_caiming);
        et_xiangqing = findViewById(R.id.et_jieshao);
        iv_tupian = findViewById(R.id.iv_tupian);
        commit1 = findViewById(R.id.commit1);
        iv_tupian.setOnClickListener(this);
        commit1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_tupian:
                PictureSelector
                        .create(AddcaidanActivity.this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture();
                break;
            case R.id.commit1:
                String price1 = et_price.getText().toString();
                String caiming = et_caiming.getText().toString();
                String xiangqing = et_xiangqing.getText().toString();
                Qiye medic = new Qiye();
                medic.setPath(path);
                medic.setPrice(price1);
                medic.setCaiming(caiming);
                medic.setJieshao(xiangqing);
                int i = MedicDbutils.getInstance(getApplicationContext()).insert(medic);
                if (i == 0) {
                    showToast("添加成功");
//                    Intent intent = new Intent();
//                    intent.putExtra("medic", medic);
//                    setResult(RESULT_OK,intent);
//                    finish();

                    finish();
                } else {
                    showToast("添加失败");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                PictureBean pictureBean = data.getParcelableExtra(PictureSelector.PICTURE_RESULT);
                path = pictureBean.getPath();
                Glide.with(this).load(pictureBean.getPath()).into(iv_tupian);
            }
        }
    }
}