package com.cloud.music.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hwangjr.rxbus.RxBus;

import com.cloud.music.utils.PermissionReq;
import com.cloud.music.utils.binding.ViewBinder;

/**
 * 基类
 */
public abstract class BaseFragment extends Fragment {
    protected Handler handler;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        handler = new Handler(Looper.getMainLooper());
        ViewBinder.bind(this, getView());
        RxBus.get().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setListener();
    }

    protected void setListener() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }
}
