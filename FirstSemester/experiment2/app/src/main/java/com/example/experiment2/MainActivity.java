package com.example.experiment2;

import android.content.Intent;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String TAG="LIFTCYCLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"(1)onCreate()");
        Button button1=(Button)findViewById(R.id.btn_finish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"(2)onStart()");
    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i(TAG,"(3)onRestoreInstanceState()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"(4)onResume()");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG,"(5)onSaveInstanceState()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"(6)onRestart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"(7)onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"(8)onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"(9)onDestroy()");
    }
}
