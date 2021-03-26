package com.lh.lhjuzhen.ui;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.UdpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PingjieActivity extends BaseActivity {

    @BindView(R.id.et_x)
    EditText et_x;
    @BindView(R.id.et_y)
    EditText et_y;
    @BindView(R.id.et_ping_1)
    EditText et_ping_1;
    @BindView(R.id.et_ping_2)
    EditText et_ping_2;
    @BindView(R.id.et_ping_3)
    EditText et_ping_3;
    @BindView(R.id.et_ping_4)
    EditText et_ping_4;
    @BindView(R.id.et_ping_5)
    EditText et_ping_5;
    @BindView(R.id.et_ping_6)
    EditText et_ping_6;
    @BindView(R.id.et_ping_7)
    EditText et_ping_7;
    @BindView(R.id.et_ping_8)
    EditText et_ping_8;
    @BindView(R.id.et_ping_9)
    EditText et_ping_9;
    @BindView(R.id.et_ping_10)
    EditText et_ping_10;
    @BindView(R.id.et_ping_11)
    EditText et_ping_11;
    @BindView(R.id.et_ping_12)
    EditText et_ping_12;
    @BindView(R.id.et_ping_13)
    EditText et_ping_13;
    @BindView(R.id.et_ping_14)
    EditText et_ping_14;
    @BindView(R.id.et_ping_15)
    EditText et_ping_15;
    @BindView(R.id.et_ping_16)
    EditText et_ping_16;

    private String jzIp;
    private ArrayList<String> pingkeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjie);
        ButterKnife.bind(this);

        jzIp = this.getIntent().getStringExtra("jzip");
        pingkeys = new ArrayList<String>();
        pingkeys.add(0, "01");
        pingkeys.add(1, "01");
        pingkeys.add(2, "01");
        pingkeys.add(3, "01");
        pingkeys.add(4, "01");
        pingkeys.add(5, "01");
        pingkeys.add(6, "01");
        pingkeys.add(7, "01");
        pingkeys.add(8, "01");
        pingkeys.add(9, "01");
        pingkeys.add(10, "01");
        pingkeys.add(11, "01");
        pingkeys.add(12, "01");
        pingkeys.add(13, "01");
        pingkeys.add(14, "01");
        pingkeys.add(15, "01");
    }

    @OnTextChanged(value = R.id.et_ping_1, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged1(Editable editable) {
        setStringList(0, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_2, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged2(Editable editable) {
        setStringList(1, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_3, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged3(Editable editable) {
        setStringList(2, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_4, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged4(Editable editable) {
        setStringList(3, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_5, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged5(Editable editable) {
        setStringList(4, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_6, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged6(Editable editable) {
        setStringList(5, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_7, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged7(Editable editable) {
        setStringList(6, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_8, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged8(Editable editable) {
        setStringList(7, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_9, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged9(Editable editable) {
        setStringList(8, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_10, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged10(Editable editable) {
        setStringList(9, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_11, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged11(Editable editable) {
        setStringList(10, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_12, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged12(Editable editable) {
        setStringList(11, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_13, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged13(Editable editable) {
        setStringList(12, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_14, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged14(Editable editable) {
        setStringList(13, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_15, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged15(Editable editable) {
        setStringList(14, editable.toString());
    }

    @OnTextChanged(value = R.id.et_ping_16, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void pingTextChanged16(Editable editable) {
        setStringList(15, editable.toString());
    }


    private void setStringList(int i, String str) {
        if (str.isEmpty()) {
            pingkeys.set(i, "01");
        } else {
            String hex = Integer.toHexString(Integer.parseInt(str));
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            pingkeys.set(i, hex);
        }
    }

    @OnClick(R.id.btn_set_pj)
    public void btn_set_pj() {
        sendUDPmsg("BB0000530000005301");
    }

    @OnClick(R.id.btn_pj_hy)
    public void btn_pj_hy() {
        sendUDPmsg("BB0000530000005300");
    }

    private void sendUDPmsg(String msg) {
        if (et_x.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入水平方向屏个数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_y.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入垂直方向屏个数", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_x.getText().toString().trim().equals("1") && et_y.getText().toString().trim().equals("1")) {
            Toast.makeText(this, "至少有一个方向大于1", Toast.LENGTH_SHORT).show();
            return;
        }
        int numx = Integer.parseInt(et_x.getText().toString().trim());
        int numy = Integer.parseInt(et_y.getText().toString().trim());
        int numping = numx * numy;

        StringBuffer buffer = new StringBuffer();
        buffer.append(msg);
        buffer.append("0" + numx);
        buffer.append("0" + numy);

        for (int i = 0; i < numping; i++) {
            buffer.append(pingkeys.get(i));
        }
        buffer.append("DD");
        byte[] data = UdpUtils.DataToBytes(buffer.toString());
        UdpUtils.ClientSendMsg(jzIp, data);
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