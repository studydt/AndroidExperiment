package com.example.experiment1;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import androidx.annotation.Nullable;

public class ClockService extends Service {
    private final IBinder iBinder = new MyBinder();

    private String TAG = "TimeService";
    private int count;
    private boolean quit;

    public class MyBinder extends Binder {
        public int getCount() {
            // get the counting status：count
            return count;
        }

        public void setCount(int Count) {
            count = Count;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "TimeService->onCreate");
        new Thread() {
            @Override
            public void run() {
                while (!quit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    public class LocalBinder extends Binder {
        ClockService getService() {
            System.out.println("Service-->LocalBinder-->getService");
            return ClockService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Service-->onBind");
        Log.i(TAG, "TimeService->onBind");
        return iBinder;
    }

    // invoke when the service unbind
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Service is Unbinded");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        System.out.println("Service is Destroyed");
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        this.quit = true;
        System.out.println("Service is ReBinded");
    }
}