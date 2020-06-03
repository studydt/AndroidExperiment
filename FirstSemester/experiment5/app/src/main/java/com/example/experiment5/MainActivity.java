package com.example.experiment5;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //控件对象
    private TextView tochView;
    //手势分析对象
    private GestureDetector gestureDetector=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tochView=(TextView) findViewById(R.id.tochView);
        //创建手势分析对象--同时设定手势识别监听器，用于监听识别结果,参数--Context，OnGestureListener()
        gestureDetector=new GestureDetector(this, new  GestureScaleListener());
        //View必须设置longClickable为true，否则手势识别无法正确工作(允许长按)
        tochView.setLongClickable(true);
        tochView.setOnTouchListener(new View.OnTouchListener() {
            double sx,sy,ex,ey,vx,vy,time;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        sx=motionEvent.getX();
                        sy=motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        VelocityTracker velocity = VelocityTracker.obtain();
                        velocity.addMovement(motionEvent);
                        velocity.computeCurrentVelocity(1000);
                        vx=velocity.getXVelocity();
                        vy=velocity.getYVelocity();
                        break;
                    case MotionEvent.ACTION_UP:
                        ex=motionEvent.getX();
                        ey=motionEvent.getY();
                        String s="";
                        if (sy- ey > 30
                                && Math.abs(vy) > 50) { //速度和距离都超过一定数值时才算滑动
                            // Fling up
                            s="上滑";
                        } else  if (ey- sy  >30
                                && Math.abs(vy) > 50) {
                            // Fling down
                            s="下滑";
                        }else if(sx-ex>30
                                && Math.abs(vx) > 50){
                            s="左滑";
                        }else if(ex-sx>30
                                && Math.abs(vx) > 50){
                            s="右滑";
                        }
                        else
                            s="未定";
                        tochView.setText(s);
                        System.out.println(s);
                        break;
                }
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }
    class GestureScaleListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }
        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }
        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float v, float v1) {
            return false;
        }
        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vx, float vy) {
            System.out.println("onFling");
            String s="";
            if (e1.getY() - e2.getY() > 30
                    && Math.abs(vy) > 50) { //速度和距离都超过一定数值时才算滑动
                // Fling up
                s="上滑";
            } else if (e2.getY() - e1.getY() >30
                    && Math.abs(vy) > 50) {
                // Fling down
                s="下滑";
            }else if(e1.getX()-e2.getX()>30
                    && Math.abs(vx) > 50){
                s="左滑";
            }else if(e2.getX()-e1.getX()>30
                    && Math.abs(vx) > 50){
                s="右滑";
            }
            else
                s="未定";
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
