package com.example.lab20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //khai báo MyAsyncTask
    MyAsyncTask mytt;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.button1);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStart();
            }
        });
    }
    private void doStart(){
        //truyền this (chính là MainActivity hiện tại) qua background Thread
        mytt = new MyAsyncTask(this);
        //kích hoạt tiến trình
        //khi gọi hàm này thì onPressExcute của mytt sẽ thực thi trước
        mytt.execute();
    }
}