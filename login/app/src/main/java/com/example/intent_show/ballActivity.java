package com.example.intent_show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ballActivity extends AppCompatActivity {
    public int IDRadio = 2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balllayout);

                //单选按钮
        TextView textView = (TextView) findViewById(R.id.textView3);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            //Auto-generated method stub
            switch (checkedId) {
                case R.id.radioButton11:
                    textView.setText("您选择的是篮球");
                    break;
                case R.id.radioButton22:
                    textView.setText("您选择的是足球");
                    break;
                case R.id.radioButton33:
                    textView.setText("您选择的是羽毛球");
                    break;
                default:
                    break;
            }
        });

    }
}
