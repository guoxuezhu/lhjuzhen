package com.lh.lhjuzhen.data.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class VideoIP {


    public String name;
    public String outName;
    public String strIp;


    @Generated(hash = 47416758)
    public VideoIP(String name, String outName, String strIp) {
        this.name = name;
        this.outName = outName;
        this.strIp = strIp;
    }


    @Generated(hash = 998017859)
    public VideoIP() {
    }


    @Override
    public String toString() {
        return "VideoIP{" +
                "name='" + name + '\'' +
                ", outName='" + outName + '\'' +
                ", strIp='" + strIp + '\'' +
                '}';
    }


    public String getName() {
        return this.name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getOutName() {
        return this.outName;
    }


    public void setOutName(String outName) {
        this.outName = outName;
    }


    public String getStrIp() {
        return this.strIp;
    }


    public void setStrIp(String strIp) {
        this.strIp = strIp;
    }
}