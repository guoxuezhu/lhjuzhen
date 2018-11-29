package com.lh.lhjuzhen.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.DisplayTools;
import com.lh.lhjuzhen.utils.ELog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        host_ip.setText(DisplayTools.getIPAddress(this));
    }

    @OnClick(R.id.btn_dan)
    public void btn_dan() {
        if (et_in.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输入口", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_out.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输出口", Toast.LENGTH_SHORT).show();
            return;
        }

        String strCommand = "";
        if (et_in.getText().toString().trim().length() == 1) {
            if (et_out.getText().toString().trim().length() == 1) {
                strCommand = "BB040002" + "0" + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
            } else {
                strCommand = "BB040002" + "0" + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
            }
        } else {
            if (et_out.getText().toString().trim().length() == 1) {
                strCommand = "BB040002" + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
            } else {
                strCommand = "BB040002" + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
            }
        }

        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }


    @OnClick(R.id.btn_quan)
    public void btn_quan() {
        if (et_in.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输入口", Toast.LENGTH_SHORT).show();
            return;
        }

        String strCommand = "";
        if (et_in.getText().toString().trim().length() == 1) {
            strCommand = "BB030009" + "0" + et_in.getText().toString().trim() + "00" + "0055";
        } else {
            strCommand = "BB030009" + et_in.getText().toString().trim() + "00" + "0055";
        }
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }

    private byte[] DataToBytes(String strdata) {
        byte[] bytes = new byte[strdata.length() / 2];
        for (int i = 0; i < strdata.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(strdata.substring(i, i + 2), 16);
        }
        return bytes;
    }

    private void ClientSendMsg(final byte[] bytesend) {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                DatagramSocket datagramSocket = null;
                try {
                    ELog.d("=======0000000===========");
                    datagramSocket = new DatagramSocket();
                    DatagramPacket dp = new DatagramPacket(bytesend, bytesend.length);
                    dp.setSocketAddress(new InetSocketAddress(et_ip.getText().toString(), 8806));
                    datagramSocket.send(dp);//发送一条信息
                    ELog.d("=======4444444===========");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    ELog.d("=======UnknownHostException===========");
                } catch (SocketException e) {
                    e.printStackTrace();
                    ELog.d("=======SocketException===========");
                } catch (IOException e) {
                    e.printStackTrace();
                    ELog.d("=======IOException===========");
                } catch (Exception e) {
                    e.printStackTrace();
                    ELog.d("=======Exception===========");
                } finally {
                    if (datagramSocket != null) {
                        datagramSocket.close();
                    }
                    if (timer != null) {
                        timer.cancel();
                    }
                }

            }
        }, 1);

    }

}
