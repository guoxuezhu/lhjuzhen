package com.lh.lhjuzhen.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;


import com.lh.lhjuzhen.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddIpDialog extends Dialog {


    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.out_name)
    EditText out_name;
    @BindView(R.id.et_ip_add)
    EditText et_ip_add;


    private Context mContext;
    private DialogCallBack mDialogCallBack;

    public AddIpDialog(Context context, DialogCallBack dialogCallBack) {
        super(context, R.style.FullHeightDialog);
        mContext = context;
        mDialogCallBack = dialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ip_dialog_view);
        ButterKnife.bind(this);
    }

    public interface DialogCallBack {
        void addIpInfo(String name, String outName, String ip);
    }

    @OnClick(R.id.dialog_btn_no)
    public void dialog_btn_no() {
        dismiss();
    }

    @OnClick(R.id.dialog_btn_ok)
    public void dialog_btn_ok() {
        if (et_name.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入名称", Toast.LENGTH_SHORT).show();
            return;
        }

        if (out_name.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入输出口", Toast.LENGTH_SHORT).show();
            return;
        }

        if (et_ip_add.getText().toString().trim().equals("")) {
            Toast.makeText(mContext, "请输入IP", Toast.LENGTH_SHORT).show();
            return;
        }

        mDialogCallBack.addIpInfo(et_name.getText().toString(), out_name.getText().toString(),
                et_ip_add.getText().toString());

    }

}
