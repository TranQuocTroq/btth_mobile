package com.example.request_processresults;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtResult;
    Button btnRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);
        btnRequest = findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, SubActivity.class);
                //lấy dữ liệu a,b
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                //đẩy dữ liệu vào intent
                myintent.putExtra("soa", a);
                myintent.putExtra("sob", b);
                //khởi động intent và chờ kết quả
                startActivityForResult(myintent, 99);
            }
        });
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 && resultCode==33){
            int sum = data.getIntExtra("kq", 0);
            edtResult.setText("Tổng 2 số là: "+sum);
        }
        if(requestCode==99 && resultCode==34){
            int sub = data.getIntExtra("kq", 0);
            edtResult.setText("Hiệu 2 số là: "+sub);
        }
    }
}