package com.example.lab13_5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.BufferUnderflowException;

public class subActivity extends Activity {
    private Bundle extra;
    TextView txtname2;
    ImageView img2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.childlayout);
        txtname2 = (TextView) findViewById(R.id.textView2);
        img2 = (ImageView) findViewById(R.id.imageView2);
        extra = getIntent().getExtras();
        int position = extra.getInt("TITLE");
        txtname2.setText(MainActivity.arrayName[position]);
        img2.setImageResource(MainActivity.imageName[position]);
    }
}
