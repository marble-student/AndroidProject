package com.example.dial;

import android.net.Uri;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText1; //电话输入框
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.获取界面的文本输入框,取出电话号码
        //得到界面上的按钮
        Button bt_dail = (Button) findViewById(R.id.bt_dail);
        //得到界面上的文本输入框
        editText1 = (EditText) findViewById(R.id.editText1);

        //获取文本框中的号
        //给按钮注册点击的监听器
        bt_dail.setOnClickListener(new MyListener());
    }
    //打电话的监听事件
    private class MyListener implements OnClickListener{

        @Override
        public void onClick(View v){
            //获取编辑框中的内容
            String number = editText1.getText().toString();
            //创建一个意图
            Intent intent = new Intent();
            //打电话的行为
            intent.setAction(Intent.ACTION_CALL);
            //拨打电话
            intent.setData(Uri.parse("tel:"+number));
            //启动意图
            startActivity(intent);
        }
    }
}