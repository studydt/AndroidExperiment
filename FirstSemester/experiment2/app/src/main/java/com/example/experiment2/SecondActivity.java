package com.example.experiment2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static String TAG="LIFTCYCLE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"(1)onCreate()2");
        setContentView(R.layout.activity_second);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"(2)onStart()2");
    }
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i(TAG,"(3)onRestoreInstanceState()2");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"(4)onResume()2");
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG,"(5)onSaveInstanceState()2");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"(6)onRestart()2");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"(7)onPause()2");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"(8)onStop()2");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"(9)onDestroy()2");
    }
}