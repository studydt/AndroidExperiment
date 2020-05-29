package com.example.experiment2;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    private static String TAG="LIFTCYCLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.i(TAG,"(1)onCreate()3");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"(2)onStart()3");
    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i(TAG,"(3)onRestoreInstanceState()3");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"(4)onResume()3");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG,"(5)onSaveInstanceState()3");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"(6)onRestart()3");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"(7)onPause()3");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"(8)onStop()3");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"(9)onDestroy()3");
    }
}
