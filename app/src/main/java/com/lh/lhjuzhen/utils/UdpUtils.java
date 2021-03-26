package com.lh.lhjuzhen.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class UdpUtils {

    private static Timer timer1;

    // 进制转换
    public static byte[] DataToBytes(String strdata) {
        byte[] bytes = new byte[strdata.length() / 2];
        for (int i = 0; i < strdata.length(); i = i + 2) {
            bytes[i / 2] = (byte) Integer.parseInt(strdata.substring(i, i + 2), 16);
        }
        return bytes;
    }


    public static void ClientSendMsg(final String jzIp, final byte[] bytesend) {
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {

                DatagramSocket datagramSocket = null;
                try {
                    ELog.d("=======0000000===========");
                    datagramSocket = new DatagramSocket();
                    DatagramPacket dp = new DatagramPacket(bytesend, bytesend.length);
                    dp.setSocketAddress(new InetSocketAddress(jzIp, 8806));
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
                    if (timer1 != null) {
                        timer1.cancel();
                    }
                }

            }
        }, 1);
    }
}
