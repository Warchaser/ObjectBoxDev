package com.warchaser.objcetboxdev.app;

import android.app.Application;

import com.warchaser.objcetboxdev.BuildConfig;
import com.warchaser.objcetboxdev.nosql.entity.MyObjectBox;
import com.warchaser.objcetboxdev.util.NLog;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class App extends Application {

    private BoxStore mBoxStore;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        initNoSql();
    }

    private void initNoSql(){
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
        if (BuildConfig.DEBUG){
            String result = "";
            result = new AndroidObjectBrowser(mBoxStore).start(this) ? "ObjectBox debug mode started!" : "ObjectBox debug mode started failed!";
            NLog.e("ObjectBox", result);
        }
    }

    public static App getInstance(){
        return mApp;
    }

    public synchronized BoxStore getBoxStore(){
        if(mBoxStore == null){
            initNoSql();
        }

        return mBoxStore;
    }
}
