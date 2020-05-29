package com.example.experiment1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    public static TextView clock;
    ClockService clockService = null;
    private int sign = 0;
    ClockService.MyBinder binder;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            clock.setText("" + binder.getCount());
            System.out.println(binder.getCount());
        }
    };

    public class TimeThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    mHandler.sendMessage(msg);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("--Service Connected--");
            binder = (ClockService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("--Service Disconnected--");
            clockService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent serviceIntent = new Intent(this, ClockService.class);
        Button button = findViewById(R.id.button);
        Button stop = findViewById(R.id.stop);
        Button quick = findViewById(R.id.quick);
        clock = findViewById(R.id.clock);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        quick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.setCount(binder.getCount() + 10);
                Toast.makeText(MainActivity.this, "skip 10s", Toast.LENGTH_LONG).show();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
                Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_LONG).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_LONG).show();
                bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        new TimeThread().start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
