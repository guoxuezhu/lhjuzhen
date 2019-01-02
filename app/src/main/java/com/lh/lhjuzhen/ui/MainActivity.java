package com.lh.lhjuzhen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.lh.lhjuzhen.R;
import com.lh.lhjuzhen.utils.DisplayTools;
import com.lh.lhjuzhen.utils.ELog;

import org.videolan.vlc.VlcVideoView;
import org.videolan.vlc.listener.MediaListenerEvent;

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

public class MainActivity extends BaseActivity implements MediaListenerEvent {

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

    @BindView(R.id.et_video_ip)
    EditText et_video_ip;
    @BindView(R.id.vlc_video_view)
    VlcVideoView vlc_video_view;

    @BindView(R.id.rbtn_av)
    RadioButton rbtn_av;      //音视频
    @BindView(R.id.rbtn_v_vga)
    RadioButton rbtn_v_vga;   //视频
    @BindView(R.id.rbtn_audio)
    RadioButton rbtn_audio;   //音频
    private Timer timer;

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


    @Override
    public void eventPlayInit(boolean openClose) {
        ELog.i("=======eventPlayInit============" + openClose);
        if (openClose) {
            ELog.i("=======1111111==========");
        }
    }

    @Override
    public void eventBuffing(int event, float buffing) {
        ELog.i("=======eventBuffing============" + event + "=====buffing====" + buffing);

    }

    @Override
    public void eventPlay(boolean isPlaying) {
        ELog.i("=======eventPlay============" + isPlaying);
        if (!isPlaying) {

        }
    }

    @Override
    public void eventStop(boolean isPlayError) {
        ELog.i("=======eventStop============" + isPlayError);
    }

    @Override
    public void eventError(int event, boolean show) {
        ELog.i("=======eventError============" + event + "=====show=====" + show);
    }


    @Override
    protected void onPause() {
        super.onPause();
        vlc_video_view.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vlc_video_view.startPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lh", "=======onStop============");
        vlc_video_view.onStop();
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

        String type = "";
        if (rbtn_av.isChecked()) {
            type = "01";
        } else if (rbtn_v_vga.isChecked()) {
            type = "02";
        } else if (rbtn_audio.isChecked()) {
            type = "03";
        }


        String strCommand = "";
        if (et_in.getText().toString().trim().length() == 1) {
            if (et_out.getText().toString().trim().length() == 1) {
                strCommand = "BB0400" + type + "0" + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
            } else {
                strCommand = "BB0400" + type + "0" + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
            }
        } else {
            if (et_out.getText().toString().trim().length() == 1) {
                strCommand = "BB0400" + type + et_in.getText().toString().trim() + "0" + et_out.getText().toString().trim() + "0055";
            } else {
                strCommand = "BB0400" + type + et_in.getText().toString().trim() + et_out.getText().toString().trim() + "0055";
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

        String type = "";
        if (rbtn_av.isChecked()) {
            type = "07";
        } else if (rbtn_v_vga.isChecked()) {
            type = "09";
        } else if (rbtn_audio.isChecked()) {
            type = "0A";
        }

        String strCommand = "";
        if (et_in.getText().toString().trim().length() == 1) {
            strCommand = "BB0300" + type + "0" + et_in.getText().toString().trim() + "00" + "0055";
        } else {
            strCommand = "BB0300" + type + et_in.getText().toString().trim() + "00" + "0055";
        }
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }


    @OnClick(R.id.btn_mode)
    public void btn_mode() {
        if (et_mode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入模式号", Toast.LENGTH_SHORT).show();
            return;
        }

        String strCommand = "";
        if (et_mode.getText().toString().trim().length() == 1) {
            strCommand = "BB030004" + "0" + et_mode.getText().toString().trim() + "00" + "0055";
        } else {
            strCommand = "BB030004" + et_mode.getText().toString().trim() + "00" + "0055";
        }
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }

    @OnClick(R.id.btn_save)
    public void btn_save() {
        if (et_mode.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入模式号", Toast.LENGTH_SHORT).show();
            return;
        }

        String strCommand = "";
        if (et_mode.getText().toString().trim().length() == 1) {
            strCommand = "BB030006" + "0" + et_mode.getText().toString().trim() + "00" + "0055";
        } else {
            strCommand = "BB030006" + et_mode.getText().toString().trim() + "00" + "0055";
        }
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
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

        String strCommand = "";
        if (et_in.getText().toString().trim().length() == 1) {
            strCommand = "BB0300" + type + "0" + et_in.getText().toString().trim() + "00" + "0055";
        } else {
            strCommand = "BB0300" + type + et_in.getText().toString().trim() + "00" + "0055";
        }
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }

    @OnClick(R.id.btn_buzzer)
    public void btn_buzzer() {
        String strCommand = "BB02000F00000055";
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
    }

    @OnClick(R.id.btn_lock)
    public void btn_lock() {
        String strCommand = "BB02001000000055";
        byte[] data = DataToBytes(strCommand);
        ClientSendMsg(data);
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

    @OnClick(R.id.btn_rtsp)
    public void btn_rtsp() {
//        Intent intent = new Intent(this, AddVideoActivity.class);
//        startActivity(intent);
        if (et_video_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入输出口IP", Toast.LENGTH_SHORT).show();
            return;
        }
        vlc_video_view.setMediaListenerEvent(this);
        vlc_video_view.setPath("rtsp://" + et_video_ip.getText().toString().trim() + "/c=0&s=0");
        vlc_video_view.startPlay();
    }

    private byte[] DataToBytes(String strdata) {
        byte[] bytes = new byte[strdata.length() / 2];
        for (int i = 0; i < strdata.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(strdata.substring(i, i + 2), 16);
        }
        return bytes;
    }

    private void ClientSendMsg(final byte[] bytesend) {
        if (et_ip.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "请输入矩阵IP", Toast.LENGTH_SHORT).show();
            return;
        }
        timer = new Timer();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
        vlc_video_view.onDestory();
    }
}
