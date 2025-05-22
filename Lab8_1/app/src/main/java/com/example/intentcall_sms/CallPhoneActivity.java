package com.example.intentcall_sms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CallPhoneActivity extends AppCompatActivity {
    EditText edtCall;
    ImageButton btnCall;
    Button btnBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
        edtCall = findViewById(R.id.edtCall);
        btnCall = findViewById(R.id.btnCall);
        btnBack1 = findViewById(R.id.btnBack1);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+edtCall.getText().toString()));
                //yêu cầu ng dùng đồng ý quyền truy cập vào tính năng gọi điện
                if(ActivityCompat.checkSelfPermission(CallPhoneActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(CallPhoneActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(callintent);
            }
        });
        btnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}