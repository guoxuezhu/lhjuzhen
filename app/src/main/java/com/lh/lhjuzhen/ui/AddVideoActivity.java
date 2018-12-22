package com.lh.lhjuzhen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lh.lhjuzhen.LHJZApplication;
import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.adapter.IPAdapter;
import com.lh.lhjuzhen.data.DbDao.VideoIPDao;
import com.lh.lhjuzhen.data.model.VideoIP;
import com.lh.lhjuzhen.utils.AddIpDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVideoActivity extends BaseActivity implements AddIpDialog.DialogCallBack, IPAdapter.CallBack {

    @BindView(R.id.ip_recyclerView)
    RecyclerView ip_recyclerView;

    private AddIpDialog addIpDialog;
    private VideoIPDao videoIPDao;
    private IPAdapter ipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        ButterKnife.bind(this);

        videoIPDao = LHJZApplication.getDaoSession().getVideoIPDao();
        initRcyclerView();
    }

    private void initRcyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ip_recyclerView.setLayoutManager(manager);
        ipAdapter = new IPAdapter(this, videoIPDao.loadAll(), this);
        ip_recyclerView.setAdapter(ipAdapter);
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
        videoIPDao.insert(new VideoIP(name, outName, ip));
        closeDialog();
    }

    private void closeDialog() {
        ipAdapter.setData(videoIPDao.loadAll());
        if (addIpDialog != null) {
            addIpDialog.dismiss();
            addIpDialog = null;
        }
    }


    @Override
    public void onClickItem(VideoIP item) {
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra("strIp", item.strIp);
        startActivity(intent);
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
