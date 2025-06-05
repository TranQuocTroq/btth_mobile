package com.example.lab13_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    TextView txt_subphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        txt_subphone = findViewById(R.id.txt_subphone);
        Intent myintent = getIntent();
        String namephone = myintent.getStringExtra("name");
        txt_subphone.setText(namephone);
    }
}