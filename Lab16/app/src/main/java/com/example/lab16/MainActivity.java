package com.example.lab16;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtResult;
    Button btnSum, btnClear;
    TextView txthistory;
    String history="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtResult = findViewById(R.id.edtResult);
        btnSum = findViewById(R.id.btnSum);
        btnClear = findViewById(R.id.btnClear);
        txthistory = findViewById(R.id.txtHistory);

        //lấy lại dl trong SharedPreferences
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        history = myprefs.getString("ls", "");
        txthistory.setText(history);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                int rs = a + b;
                edtResult.setText(rs+"");
                history += a + " + " + b + " = " + rs;
                txthistory.setText(history);
                history += "\n"; //xuống dòng
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history="";
                txthistory.setText(history);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs = getSharedPreferences("mysave", MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls", history);
        myedit.commit();
    }
}