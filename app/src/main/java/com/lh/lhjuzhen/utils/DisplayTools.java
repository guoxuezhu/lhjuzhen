package com.lh.lhjuzhen.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * Created by gxz
 */
public class DisplayTools {

    public static String getIPAddress(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ip = wifiInfo.getIpAddress();
                return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + (ip >> 24 & 0xFF);
            } else if (info.getType() == ConnectivityManager.TYPE_ETHERNET) {

                String hostIp = null;
                try {
                    Enumeration nis = NetworkInterface.getNetworkInterfaces();
                    InetAddress ia = null;
                    while (nis.hasMoreElements()) {
                        NetworkInterface ni = (NetworkInterface) nis.nextElement();
                        Enumeration<InetAddress> ias = ni.getInetAddresses();
                        while (ias.hasMoreElements()) {
                            ia = ias.nextElement();
                            if (ia instanceof Inet6Address) {
                                continue;// skip ipv6
                            }
                            String ip = ia.getHostAddress();
                            if (!"127.0.0.1".equals(ip)) {
                                hostIp = ia.getHostAddress();
                                break;
                            }
                        }
                    }
                } catch (SocketException e) {
                    ELog.e("========SocketException====");
                    e.printStackTrace();
                }
                return hostIp;

            }
        } else {
            ELog.d("======当前无网络连接,请在设置中打开网络====");
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }


    /**
     * 判断网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * @return 包
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }


//    /**
//     * @return uuid
//     */
//    public static String getUdid(SharePreferenceUtil prefs) {
//        if (prefs.getUdid() == null) {
//            prefs.setUdid(java.util.UUID.randomUUID().toString());
//        }
//        return prefs.getUdid();
//    }


    private static Runtime run = Runtime.getRuntime();//获取当前运行环境，来执行ping，相当于windows的cmd

    private static Process proc = null;

    private static String ping = "ping -c 1 -w 0.5 ";//其中 -c 1为发送的次数，-w 表示发送后等待响应的时间

    private static int j;//存放ip最后一位地址 0-255


    /**
     * 扫描局域网内ip
     * locAddress 本地ip前缀  如 192.168.0.
     */
    public static String searchIp(final String locAddress) {
        for (int i = 0; i < 256; i++) {//创建256个线程分别去ping
            j = i;
            new Thread(new Runnable() {
                public void run() {

                    String current_ip = locAddress + j;

                    String p = ping + current_ip;

                    try {
                        proc = run.exec(p);
                        int result = proc.waitFor();
                        if (result == 0) {
//                            Log.i("IP", "连接成功:" + current_ip);
                        } else {
//                            Log.i("IP", "连接失败:" + current_ip);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    } finally {
                        proc.destroy();
                    }
                }
            }).start();

        }
        return null;
    }
}
