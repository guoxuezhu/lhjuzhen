package com.lh.lhjuzhen.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.UdpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.et_in_1)
    EditText et_in_1;
    @BindView(R.id.et_in_2)
    EditText et_in_2;
    @BindView(R.id.et_out_1)
    EditText et_out_1;
    @BindView(R.id.et_out_2)
    EditText et_out_2;
    @BindView(R.id.et_time)
    EditText et_time;
    private String strIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


        strIP = getIntent().getStringExtra("ipStr");
    }


    @OnClick(R.id.btn_setting)
    public void btn_setting() {
        if (et_in_1.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入起始输入口", Toast.LENGTH_SHORT).show();
            return;
        }

        if (et_in_2.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入结束输入口", Toast.LENGTH_SHORT).show();
            return;
        }

        if (et_out_1.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入起始输出口", Toast.LENGTH_SHORT).show();
            return;
        }

        if (et_out_2.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入结束输出口", Toast.LENGTH_SHORT).show();
            return;
        }

//        String in1 = "";
//        if (et_in_1.getText().toString().trim().length() == 1) {
//            in1 = "0" + et_in_1.getText().toString().trim();
//        } else {
//            in1 = et_in_1.getText().toString().trim();
//        }

        String in1 = Integer.toHexString(Integer.parseInt(et_in_1.getText().toString().trim()));
        if (in1.length() == 1) {
            in1 = "0" + in1;
        }

        String in2 = Integer.toHexString(Integer.parseInt(et_in_2.getText().toString().trim()));
        if (in2.length() == 1) {
            in2 = "0" + in2;
        }

//        String in2 = "";
//        if (et_in_2.getText().toString().trim().length() == 1) {
//            in2 = "0" + et_in_2.getText().toString().trim();
//        } else {
//            in2 = et_in_2.getText().toString().trim();
//        }


//        String out1 = "";
//        if (et_out_1.getText().toString().trim().length() == 1) {
//            out1 = "0" + et_out_1.getText().toString().trim();
//        } else {
//            out1 = et_out_1.getText().toString().trim();
//        }

        String out1 = Integer.toHexString(Integer.parseInt(et_out_1.getText().toString().trim()));
        if (out1.length() == 1) {
            out1 = "0" + out1;
        }

        String out2 = Integer.toHexString(Integer.parseInt(et_out_2.getText().toString().trim()));
        if (out2.length() == 1) {
            out2 = "0" + out2;
        }

//        String out2 = "";
//        if (et_out_2.getText().toString().trim().length() == 1) {
//            out2 = "0" + et_out_2.getText().toString().trim();
//        } else {
//            out2 = et_out_2.getText().toString().trim();
//        }


//        String strCommand1 = "BB040011" + in1 + in2 + "0055";
//        byte[] data1 = DataToBytes(strCommand1);
//        ClientSendMsg(data1);
//
//        String strCommand2 = "BB040012" + out1 + out2 + "0055";
//        byte[] data2 = DataToBytes(strCommand2);
//        ClientSendMsg(data2);

        byte[] data1 = UdpUtils.DataToBytes("BB040011" + in1 + in2 + "0055");
        UdpUtils.ClientSendMsg(strIP, data1);

        byte[] data2 = UdpUtils.DataToBytes("BB040012" + out1 + out2 + "0055");
        UdpUtils.ClientSendMsg(strIP, data2);
    }


    @OnClick(R.id.btn_start)
    public void btn_start() {
        if (et_time.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入间隔时间", Toast.LENGTH_SHORT).show();
            return;
        }

        String time = Integer.toHexString(Integer.parseInt(et_time.getText().toString().trim()));
        if (time.length() == 1) {
            time = "0" + time;
        }
        byte[] data = UdpUtils.DataToBytes("BB03000D0000" + time + "55");
        UdpUtils.ClientSendMsg(strIP, data);

//        String strCommand = "";
//        if (et_time.getText().toString().trim().length() == 1) {
//            strCommand = "BB03000D0000" + "0" + et_time.getText().toString().trim() + "55";
//        } else {
//            strCommand = "BB03000D0000" + et_time.getText().toString().trim() + "55";
//        }
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
    }


    @OnClick(R.id.btn_stop)
    public void btn_stop() {
//        String strCommand = "BB02000E00000055";
//        byte[] data = DataToBytes(strCommand);
//        ClientSendMsg(data);
        byte[] data = UdpUtils.DataToBytes("BB02000E00000055");
        UdpUtils.ClientSendMsg(strIP, data);
    }


//    private byte[] DataToBytes(String strdata) {
//        byte[] bytes = new byte[strdata.length() / 2];
//        for (int i = 0; i < strdata.length(); i = i + 2) {
//            bytes[i / 2] = (byte) Integer.parseInt(strdata.substring(i, i + 2), 16);
//        }
//        return bytes;
//    }
//
//    private void ClientSendMsg(final byte[] bytesend) {
//        timer1 = new Timer();
//        timer1.schedule(new TimerTask() {
//            @Override
//            public void run() {
//
//                DatagramSocket datagramSocket = null;
//                try {
//                    ELog.d("=======0000000===========");
//                    datagramSocket = new DatagramSocket();
//                    DatagramPacket dp = new DatagramPacket(bytesend, bytesend.length);
//                    dp.setSocketAddress(new InetSocketAddress(strIP, 8806));
//                    datagramSocket.send(dp);//发送一条信息
//                    ELog.d("=======4444444===========");
//                } catch (UnknownHostException e) {
//                    e.printStackTrace();
//                    ELog.d("=======UnknownHostException===========");
//                } catch (SocketException e) {
//                    e.printStackTrace();
//                    ELog.d("=======SocketException===========");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    ELog.d("=======IOException===========");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    ELog.d("=======Exception===========");
//                } finally {
//                    if (datagramSocket != null) {
//                        datagramSocket.close();
//                    }
//                    if (timer1 != null) {
//                        timer1.cancel();
//                    }
//                }
//
//            }
//        }, 1);
//
//    }


    @OnClick(R.id.btn_back)
    public void btn_back() {
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
