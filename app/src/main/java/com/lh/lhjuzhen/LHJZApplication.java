package com.lh.lhjuzhen;

import android.app.Application;
import android.content.Context;

import com.lh.lhjuzhen.data.DbDao.DaoMaster;
import com.lh.lhjuzhen.data.DbDao.DaoSession;

/**
 * Created by gxz
 */
public class LHJZApplication extends Application {

    public static Context context;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lhzks.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }


}
