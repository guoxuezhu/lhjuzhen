package com.lh.lhjuzhen.ui;

import android.os.Bundle;
import android.widget.EditText;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.ELog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixIpActivity extends BaseActivity {


    @BindView(R.id.host_test)
    EditText host_test;


    private Timer timer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fix_ip);
        ButterKnife.bind(this);


    }


    @OnClick(R.id.btn_search)
    public void btn_search() {
        ClientSend();

    }

    @OnClick(R.id.btn_shou)
    public void btn_shou() {
        getMsg();

    }

    private void ClientSend() {

        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {


                try {
                    String test = host_test.getText().toString();
//                    byte[] msg = DataToBytes("");

//                    byte[] msg = new String(test).getBytes();


//                    msg[0] = Byte.parseByte("");

                    byte[] msg;
                    if (host_test.getText().toString().isEmpty()) {
                        msg = new byte[1];
                    } else {
                        msg = new String(test).getBytes();
                    }

                    DatagramSocket datagramSocket = new DatagramSocket();
                    DatagramPacket dp = new DatagramPacket(msg, msg.length);
                    dp.setSocketAddress(new InetSocketAddress("255.255.255.255", 60000));
                    datagramSocket.send(dp);//发送一条信息


                    ELog.d("===========msg=======111===");
                } catch (SocketException e) {
                    ELog.d("===========msg=====eee==111===");
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    ELog.d("=======222====msg==eee=====111===");
                } catch (IOException e) {
                    e.printStackTrace();
                    ELog.d("=======333====msg===ee====111===");
                }

            }
        }, 1);

    }

    private void getMsg() {

        new Thread() {
            @Override
            public void run() {
                super.run();


                DatagramPacket receive = new DatagramPacket(new byte[1024], 1024);
                DatagramSocket server = null;
                try {
                    server = new DatagramSocket(60001);
                } catch (SocketException e) {
                    e.printStackTrace();
                }

                ELog.d("==================jjjjjjjjjj================");

                while (true) {
                    try {
                        server.receive(receive);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ELog.d("=======Server receive msg:=======sdd========" + receive.getData().length);

                    byte[] recvByte = Arrays.copyOfRange(receive.getData(), 0, receive.getLength());
                    ELog.d("=======Server receive msg:=======1111111========" + recvByte.length);
                    ELog.d("=======Server receive msg:=======2222222========" + Integer.toHexString(recvByte[0] & 0xFF));
                    ELog.d("=======Server receive msg:======3333333=========" + new String(recvByte));
                    try {
                        ELog.d("=======Server receive msg:===============" + new String(recvByte, "gbk"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
    }
}
