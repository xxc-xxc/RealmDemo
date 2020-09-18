package com.uos.realmdemo;

import android.app.Application;

import io.realm.Realm;

/**
 * Create By xxc
 * Date: 2020/9/18 10:09
 * Desc:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
