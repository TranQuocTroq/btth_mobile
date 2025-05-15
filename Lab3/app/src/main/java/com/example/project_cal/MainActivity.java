package com.example.project_cal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtResult;
    Button btnAdd, btnSub, btnMulti, btnDiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMulti = findViewById(R.id.btnMulti);
        btnDiv = findViewById(R.id.btnDiv);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());

                edtResult.setText("a+b="+(a+b));
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());

                edtResult.setText("a-b="+(a-b));
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());

                edtResult.setText("a*b="+(a*b));
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edtA.getText());
                int b = Integer.parseInt("0"+edtB.getText());
                if(b==0){
                    edtResult.setText("B phai khac 0");
                }else{
                    edtResult.setText("a/b="+(a/b));
                }
            }
        });
    }
}