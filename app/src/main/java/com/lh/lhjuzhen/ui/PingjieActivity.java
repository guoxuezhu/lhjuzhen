package com.lh.lhjuzhen.ui;

import android.os.Bundle;

import com.lh.lhjuzhen.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PingjieActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjie);
        ButterKnife.bind(this);


    }



    @OnClick(R.id.btn_pj_back)
    public void btn_pj_back() {
        finish();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}