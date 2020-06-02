package com.example.experiment4;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] ids = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_equal,
                R.id.btn_clear, R.id.btn_multiply, R.id.btn_minus, R.id.btn_divide, R.id.btn_plus};
        //将所有按钮整合成一个数组
        for (int i = 0; i < ids.length; i++)
            findViewById(ids[i]).setOnClickListener(this);//给每个按钮设置一个监听事件
    }

    @Override
    public void onClick(View v) {
        TextView tv_input = (TextView) findViewById(R.id.text1);
        Button btn = (Button) v;
        String str = tv_input.getText().toString();//获取xml那里的输入
        String strButton = btn.getText().toString();//点击按钮得到的文本
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (str.equals("0"))
                    tv_input.setText(strButton);
                else
                    tv_input.setText(str + strButton);
                break;
            case R.id.btn_clear:
                tv_input.setText("0");
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                tv_input.setText(str + " " + strButton + " ");
                break;
            case R.id.btn_equal:
                MyCalc obj = new MyCalc(str);
                double ret = obj.Calc();
                int Ret = (int) ret;
                if (ret == Ret)
                    tv_input.setText(String.valueOf(Ret));
                else
                    tv_input.setText(String.valueOf(ret));
                break;
        }
    }

    class MyCalc {
        private String input;

        public MyCalc(String input) {
            this.input = input;
        }

        public double Calc() {
            if (TextUtils.isEmpty(input))
                return 0;
            double h = 0;
            Expression eh = new Expression(input);
            h = eh.calculate();
            if (Double.isNaN(h)) {
                Toast.makeText(MainActivity.this, "错误输入", Toast.LENGTH_LONG).show();
            }
            return h;
        }
    }
}
