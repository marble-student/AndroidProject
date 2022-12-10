package com.example.netstatus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NetWorkListener listener;
    private int count=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listener = new NetWorkListener(this);
        listener.register(new NetWorkListener.NetWorkStateListener() {
            @Override
            public int onWIFIStateChanged() {
                Log.d("WIFI", "MainActivity --> onStateLow--> ");
                if(count == 1) {
                    Toast.makeText(MainActivity.this, "WIFI已打开", Toast.LENGTH_LONG).show();
                    count++;
                    return 0;
                }
                if(count == 2){
                    count=0;
                    return 0;
                }
                if(count == 0){
                    Toast.makeText(MainActivity.this, "WIFI已关闭", Toast.LENGTH_SHORT).show();
                    count--;
                    return 0;
                }
                if (count == -1){
                    count=1;
                    return 0;
                }
                System.out.println("count = " + count);
                return 0;
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (listener != null) {
            listener.unregister();
        }
        super.onDestroy();
    }
}