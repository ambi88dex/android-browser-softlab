package com.example.anishkelkar.androidbrowsersoftlab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
public class History extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView[] t = new TextView[5];

        t[0] = (TextView)findViewById(R.id.t1);
        t[1] = (TextView)findViewById(R.id.t2);
        t[2] = (TextView)findViewById(R.id.t3);
        t[3] = (TextView)findViewById(R.id.t4);
        t[4] = (TextView)findViewById(R.id.t5);
//        t[0] = (TextView)findViewById(R.id.t1);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        for(int i=0; i<5; i++){
            
            String ii = String.valueOf(i);
            String str = sharedpreferences.getString(ii, null);
            t[i].setText(str);
        }
    }
}
