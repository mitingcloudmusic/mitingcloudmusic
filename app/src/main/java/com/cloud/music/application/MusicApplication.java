package com.cloud.music.application;

import android.app.Application;
import android.content.Intent;

import com.cloud.music.service.PlayService;
//import com.cloud.music.storage.db.DBManager;

/**
 * 自定义Application
 */
public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCache.get().init(this);
        ForegroundObserver.init(this);
//        DBManager.get().init(this);

        Intent intent = new Intent(this, PlayService.class);
        startService(intent);
    }
}
