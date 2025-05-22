package com.example.intentcall_sms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnCallphone, btnSendSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCallphone = findViewById(R.id.btnCallphone);
        btnSendSMS = findViewById(R.id.btnSendsms);
        btnCallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tạo mới nd
                Intent intent1 = new Intent(MainActivity.this, CallPhoneActivity.class);
                //thực thi intent1
                startActivity(intent1);
            }
        });
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, SendSMSActivity.class);
                startActivity(intent2);
            }
        });
    }
}