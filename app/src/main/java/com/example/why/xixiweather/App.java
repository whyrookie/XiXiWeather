package com.example.why.xixiweather;

import android.app.Application;

import com.example.why.xixiweather.model.entity.DaoMaster;
import com.example.why.xixiweather.model.entity.DaoSession;

import org.greenrobot.greendao.database.Database;


/**
 * Created by why on 17-2-16.
 */

public class App extends Application {
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"notes-db",null);
        Database db = helper.getWritableDb();
//
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
