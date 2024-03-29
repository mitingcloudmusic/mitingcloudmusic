package com.cloud.music.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

//import com.amap.api.location.AMapLocalWeatherLive;

import java.util.ArrayList;
import java.util.List;

import com.cloud.music.model.Music;
//import com.cloud.music.model.SheetInfo;
import com.cloud.music.utils.CoverLoader;
import com.cloud.music.storage.preference.Preferences;
import com.cloud.music.utils.ScreenUtils;
import com.cloud.music.utils.ToastUtils;

public class AppCache {
    private Context mContext;
    private final List<Music> mLocalMusicList = new ArrayList<>();
//    private final List<SheetInfo> mSheetList = new ArrayList<>();
    private final List<Activity> mActivityStack = new ArrayList<>();
//    private AMapLocalWeatherLive mAMapLocalWeatherLive;

    private AppCache() {
    }

    private static class SingletonHolder {
        private static AppCache instance = new AppCache();
    }

    public static AppCache get() {
        return SingletonHolder.instance;
    }

    public void init(Application application) {
        mContext = application.getApplicationContext();
        ToastUtils.init(mContext);
        Preferences.init(mContext);
        ScreenUtils.init(mContext);
//        CrashHandler.getInstance().init();
        CoverLoader.get().init(mContext);
        application.registerActivityLifecycleCallbacks(new ActivityLifecycle());
    }

    public Context getContext() {
        return mContext;
    }

    public List<Music> getLocalMusicList() {
        return mLocalMusicList;
    }

//    public List<SheetInfo> getSheetList() {
//        return mSheetList;
//    }

//    public void clearStack() {
//        List<Activity> activityStack = mActivityStack;
//        for (int i = activityStack.size() - 1; i >= 0; i--) {
//            Activity activity = activityStack.get(i);
//            if (!activity.isFinishing()) {
//                activity.finish();
//            }
//        }
//        activityStack.clear();
//    }

//    public AMapLocalWeatherLive getAMapLocalWeatherLive() {
//        return mAMapLocalWeatherLive;
//    }

//    public void setAMapLocalWeatherLive(AMapLocalWeatherLive aMapLocalWeatherLive) {
//        mAMapLocalWeatherLive = aMapLocalWeatherLive;
//    }

    private class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
        private static final String TAG = "Activity";

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i(TAG, "onCreate: " + activity.getClass().getSimpleName());
            mActivityStack.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i(TAG, "onDestroy: " + activity.getClass().getSimpleName());
            mActivityStack.remove(activity);
        }
    }
}
