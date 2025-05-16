package com.example.bodymass_index;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnDiagnosis;
    EditText edtName, edtHeight, edtWeight, edtBMI, edtDiagnosis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDiagnosis = findViewById(R.id.btnBMI);
        edtName = findViewById(R.id.edtName);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        edtBMI = findViewById(R.id.edtBMI);
        edtDiagnosis = findViewById(R.id.edtDiagnosis);
        btnDiagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtHeight.getText()+"");
                double W = Double.parseDouble(edtWeight.getText()+"");
                double BMI = W/Math.pow(H,2);
                String diagnosis = "";
                if(BMI<18){
                    diagnosis="Bạn gầy";
                }else if(BMI<=24.9){
                    diagnosis="Bạn bình thường";
                }else if(BMI<=29.9){
                    diagnosis="Bạn béo phì độ 1";
                }else if(BMI<=34.9){
                    diagnosis="Bạn béo phì độ 2";
                }else{
                    diagnosis="Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtDiagnosis.setText(diagnosis);
            }
        });
    }
}