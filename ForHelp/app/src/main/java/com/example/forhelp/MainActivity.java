package com.example.forhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MyBroadcastReceiverOne one;
    MyBroadcastReceiverTwo two;
    MyBroadcastReceiverThree three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver();
        init();
    }
    private void registerReceiver(){
        two = new MyBroadcastReceiverTwo();
        IntentFilter filter2 = new IntentFilter();
        filter2.setPriority(1000);
        filter2.addAction("Intercept_Stitch");
        registerReceiver(two, filter2);

        one = new MyBroadcastReceiverOne();
        IntentFilter filter1 = new IntentFilter();
        filter1.setPriority(1000);
        filter1.addAction("Intercept_Stitch");
        registerReceiver(one, filter1);

        three = new MyBroadcastReceiverThree();
        IntentFilter filter3 = new IntentFilter();
        filter3.setPriority(600);
        filter3.addAction("Intercept_Stitch");
        registerReceiver(three, filter3);
    }
    private void init(){
        Button btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("Intercept_Stitch");
                MyBroadcastReceiverThree receiver = new MyBroadcastReceiverThree();
                sendOrderedBroadcast(intent, null, receiver, null,
                        0, null, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(one);
        unregisterReceiver(two);
        unregisterReceiver(three);
    }
}