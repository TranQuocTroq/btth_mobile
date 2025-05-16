package com.example.solvequadratic_equations;

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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnContinue, btnSolve, btnExit;
    EditText edtA, edtB, edtC;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnContinue = findViewById(R.id.btnContinue);
        btnSolve = findViewById(R.id.btnSolve);
        btnExit= findViewById(R.id.btnExit);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtC = findViewById(R.id.edtC);
        txtResult = findViewById(R.id.txtResult);
        btnSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sa = edtA.getText()+"";
                String sb = edtB.getText()+"";
                String sc = edtC.getText()+"";
                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);
                String result="";
                DecimalFormat dcf = new DecimalFormat("0.00");
                if(a==0){
                    if(b==0){
                        if(c==0) {
                            result = "PT vô số nghiệm";
                        }else
                            result = "PT vô nghiệm";
                    }else{
                        result = "PT có 1 nghiệm, x="+dcf.format(-c/b);
                    }
                }else{
                    double delta = b*b-4*a*c;
                    if(delta<0){
                        result="PT vô nghiệm";
                    }else if(delta==0){
                        result="PT có nghiệm kép x1=x2="+dcf.format(-b/(2*a));
                    }else{
                        result="PT có 2 nghiệm: x1="+dcf.format((-b+Math.sqrt(delta))/(2*a))+"; x2="+dcf.format((-b-Math.sqrt(delta))/(2*a));
                    }
                }
                txtResult.setText(result);
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtA.setText("");
                edtB.setText("");
                edtC.setText("");
                edtA.requestFocus();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}