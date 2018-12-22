package com.lh.lhjuzhen.ui;

import android.content.Intent;
import android.os.Bundle;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.AddIpDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVideoActivity extends BaseActivity implements AddIpDialog.DialogCallBack {

    private AddIpDialog addIpDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        ButterKnife.bind(this);



    }


    @OnClick(R.id.add_btn)
    public void add_btn() {
        if (addIpDialog == null) {
            addIpDialog = new AddIpDialog(this, this);
        }
        if (addIpDialog != null) {
            addIpDialog.show();
            addIpDialog.setCanceledOnTouchOutside(false);
        }
    }

    @Override
    public void addIpInfo(String name, String outName, String ip) {

    }


    @OnClick(R.id.btn_back_add)
    public void btn_back_add() {
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
