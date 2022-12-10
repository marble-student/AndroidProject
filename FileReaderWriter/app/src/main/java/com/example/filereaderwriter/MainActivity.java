package com.example.filereaderwriter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private String fileName="";
    private String content="";
    EditText editText;
    EditText contentEdit;
    Button readerButton;
    Button writerButton;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.fileName);
        final EditText contentEdit = (EditText) findViewById(R.id.content);

        //读入文件内容
        Button readerButton = (Button) findViewById(R.id.read);
        //增加事件监听机制
        readerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName=editText.getText().toString();
                contentEdit.setText(readerFile(fileName));

                Toast.makeText(MainActivity.this, "读取成功", Toast.LENGTH_SHORT).show();
            }
        });
        //写入文件内容
        Button writerButton = (Button) findViewById(R.id.write);
        writerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileName=editText.getText().toString();
                content=contentEdit.getText().toString();
                if (writeFile(fileName,content))
                {
                    Toast.makeText(MainActivity.this,"写入成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
        rg = (RadioGroup) findViewById(R.id.RadioGroup);
    }
    public String getColor()
    {
        String str="";
        switch (rg.getCheckedRadioButtonId())
        {
            case R.id.Black:
                str="black";
                break;
            case R.id.Green:
                str="green";
                break;
            case R.id.Red:
                str="red";
                break;
            case R.id.Yellow:
                str="yellow";
                break;
            default:
                break;
        }
        return str;
    }
    public boolean writeFile(String fileName,String content)
    {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        SharedPreferences pre = getSharedPreferences("setup",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putString("color",getColor());
        editor.commit();

        try {
            fos = openFileOutput(fileName,MODE_APPEND);
            bos = new BufferedOutputStream(fos);
            bos.write(content.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
    public String readerFile(String fileName)
    {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String content="";
        try {
            fis = openFileInput(fileName);

            bis = new BufferedInputStream(fis);
            String realPath = getFilesDir().getPath().toString() + "/";
            File file = new File(realPath + fileName);
            byte[] buf = new byte[(int) file.length()];

            bis.read(buf);
            content = new String(buf, 0, buf.length);
            Toast.makeText(MainActivity.this, "43", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}