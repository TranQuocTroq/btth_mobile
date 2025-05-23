package com.example.lab9;
//Intent service
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageButton btnplay, btnstop;
    Boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnplay = findViewById(R.id.imgStart);
        btnstop = findViewById(R.id.imgStop);
        //xly click
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai bao Intent cong khai de khoi dong service
                Intent intent1 = new Intent(MainActivity.this, MyService.class);
                startService(intent1);
                if(flag==true){
                    btnplay.setImageResource(R.drawable.stop);
                    flag = false;
                }else{
                    btnplay.setImageResource(R.drawable.play);
                    flag = true;
                }
            }
        });
    }
}