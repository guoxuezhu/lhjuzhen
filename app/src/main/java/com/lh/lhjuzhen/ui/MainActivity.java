package com.lh.lhjuzhen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.DisplayTools;
import com.lh.lhjuzhen.utils.UdpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.host_ip)
    EditText host_ip;
    @BindView(R.id.et_ip)
    EditText et_ip;
    @BindView(R.id.et_in)
    EditText et_in;
    @BindView(R.id.et_out)
    EditText et_out;
    @BindView(R.id.et_mode)
    EditText et_mode;

    @BindView(R.id.rbtn_av)
    RadioButton rbtn_av;      //音视频
    @BindView(R.id.rbtn_v_vga)
    RadioButton rbtn_v_vga;   //视频
    @BindView(R.id.rbtn_audio)
    RadioButton rbtn_audio;   //音频

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        host_ip.setText(DisplayTools.getIPAddress(this));
        rbtn_av.setChecked(false);
        rbtn_v_vga.setChecked(true);
        rbtn_audio.setChecked(false);
    }

    @OnClick(R.id.btn_dan)
    public void btn_dan() {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_in.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输入口", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_out.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输出口", Toast.LENGTH_SHORT).show();
            return;
        }

        String type = "";
        if (rbtn_av.isChecked()) {
            type = "01";
        } else if (rbtn_v_vga.isChecked()) {
            type = "02";
        } else if (rbtn_audio.isChecked()) {
            type = "03";
        }

        String hex_in = Integer.toHexString(Integer.parseInt(et_in.getText().toString().trim()));
        if (hex_in.length() == 1) {
            hex_in = "0" + hex_in;
        }

        String hex_out = Integer.toHexString(Integer.parseInt(et_out.getText().toString().trim()));
        if (hex_out.length() == 1) {
            hex_out = "0" + hex_out;
        }
//        String strCommand = "BB0400" + type + hex_in + hex_out + "0055";
//        if (et_in.getText().toString().trim().length() == 1) {
//            if (et_out.getText().toString().trim().length() == 1) {
//                strCommand = "BB0400" + type + "0" + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
//            } else {
//                strCommand = "BB0400" + type + "0" + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
//            }
//        } else {
//            if (et_out.getText().toString().trim().length() == 1) {
//                strCommand = "BB0400" + type + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
//            } else {
//                strCommand = "BB0400" + type + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
//            }
//        }
//
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);

        byte[] data = UdpUtils.DataToBytes("BB0400" + type + hex_in + hex_out + "0055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);
    }


    @OnClick(R.id.btn_quan)
    public void btn_quan() {
        if (et_in.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输入口", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        String type = "";
        if (rbtn_av.isChecked()) {
            type = "07";
        } else if (rbtn_v_vga.isChecked()) {
            type = "09";
        } else if (rbtn_audio.isChecked()) {
            type = "0A";
        }

        String hex_in = Integer.toHexString(Integer.parseInt(et_in.getText().toString().trim()));
        if (hex_in.length() == 1) {
            hex_in = "0" + hex_in;
        }
//
//        String strCommand = "";
//        if (et_in.getText().toString().trim().length() == 1) {
//            strCommand = "BB0300" + type + "0" + et_in.getText().toString().trim() + "00" + "0055";
//        } else {
//            strCommand = "BB0300" + type + et_in.getText().toString().trim() + "00" + "0055";
//        }
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
        byte[] data = UdpUtils.DataToBytes("BB0300" + type + hex_in + "000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);
    }


    @OnClick(R.id.btn_mode)
    public void btn_mode() {
        if (et_mode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入模式号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
//
//        String strCommand = "";
//        if (et_mode.getText().toString().trim().length() == 1) {
//            strCommand = "BB030004" + "0" + et_mode.getText().toString().trim() + "00" + "0055";
//        } else {
//            strCommand = "BB030004" + et_mode.getText().toString().trim() + "00" + "0055";
//        }
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
//
        String hex_mode = Integer.toHexString(Integer.parseInt(et_mode.getText().toString().trim()));
        if (hex_mode.length() == 1) {
            hex_mode = "0" + hex_mode;
        }
        byte[] data = UdpUtils.DataToBytes("BB030004" + hex_mode + "000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);
    }

    @OnClick(R.id.btn_save)
    public void btn_save() {
        if (et_mode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入模式号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        String hex_mode = Integer.toHexString(Integer.parseInt(et_mode.getText().toString().trim()));
        if (hex_mode.length() == 1) {
            hex_mode = "0" + hex_mode;
        }
        byte[] data = UdpUtils.DataToBytes("BB030006" + hex_mode + "000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);

//        String strCommand = "";
//        if (et_mode.getText().toString().trim().length() == 1) {
//            strCommand = "BB030006" + "0" + et_mode.getText().toString().trim() + "00" + "0055";
//        } else {
//            strCommand = "BB030006" + et_mode.getText().toString().trim() + "00" + "0055";
//        }
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
    }


    @OnClick(R.id.btn_close)
    public void btn_close() {
        if (et_in.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输入口", Toast.LENGTH_SHORT).show();
            return;
        }

        String type = "";
        if (rbtn_av.isChecked()) {
            type = "05";
        } else if (rbtn_v_vga.isChecked()) {
            type = "0B";
        } else if (rbtn_audio.isChecked()) {
            type = "0C";
        }

        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        String hex_in = Integer.toHexString(Integer.parseInt(et_in.getText().toString().trim()));
        if (hex_in.length() == 1) {
            hex_in = "0" + hex_in;
        }
        byte[] data = UdpUtils.DataToBytes("BB0300" + type + hex_in + "000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);

//        String strCommand = "";
//        if (et_in.getText().toString().trim().length() == 1) {
//            strCommand = "BB0300" + type + "0" + et_in.getText().toString().trim() + "00" + "0055";
//        } else {
//            strCommand = "BB0300" + type + et_in.getText().toString().trim() + "00" + "0055";
//        }
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
    }

    @OnClick(R.id.btn_buzzer)
    public void btn_buzzer() {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        byte[] data = UdpUtils.DataToBytes("BB02000F00000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);
//
//        String strCommand = "BB02000F00000055";
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
    }

    @OnClick(R.id.btn_lock)
    public void btn_lock() {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        byte[] data = UdpUtils.DataToBytes("BB02001000000055");
        UdpUtils.ClientSendMsg(et_ip.getText().toString(), data);

//        String strCommand = "BB02001000000055";
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
    }


    @OnClick(R.id.btn_turning)
    public void btn_turning() {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra("ipStr", et_ip.getText().toString());
        startActivity(intent);
    }

    @OnClick(R.id.btn_fixip)
    public void btn_fixip() {
        Intent intent = new Intent(this, FixIpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fp_jz)
    public void fp_jz() {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, PingjieActivity.class);
        intent.putExtra("jzip", et_ip.getText().toString().trim());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
