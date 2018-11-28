package com.lh.lhjuzhen;

import android.app.Application;
import android.content.Context;

/**
 * Created by gxz
 */
public class LHJZApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }



}
