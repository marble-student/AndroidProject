package com.example.control;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
     private static final String[] DATA = new String[]{
            "101 android"," 102 j2ee","103 python"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AutoCompleteTextView atv_content =(AutoCompleteTextView)findViewById((R.id.atv_content));
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, DATA);
        atv_content.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        listView.setAdapter(adapter);
    }
}