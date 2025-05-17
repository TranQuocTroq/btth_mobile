package com.example.registerpersonal_information;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtID, edtAdd;
    CheckBox chkRNews, chkRBook, chkRCod;
    Button btnSend;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        edtID = findViewById(R.id.edtID);
        edtAdd= findViewById(R.id.edtAdd);
        chkRNews = findViewById(R.id.chkRNews);
        chkRBook = findViewById(R.id.chkRBook);
        chkRCod = findViewById(R.id.chkRCod);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }
        });
    }
    public void doShowInformation(){
        //Kiểm tra tên hợp lệ
        String name = edtName.getText().toString();
        name = name.trim();
        if(name.length()<3){
            edtName.requestFocus();
            edtName.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //kiểm tra cmnd hợp lệ
        String idC = edtID.getText().toString();
        idC = idC.trim();
        if(idC.length()!=9){
            edtID.requestFocus();
            edtID.selectAll();
            Toast.makeText(this, "ID phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //kiểm gtra bằng cấp
        String degree="";
        group = findViewById(R.id.idGroup);
        int id = group.getCheckedRadioButtonId(); //trả về id
        if(id==-1){
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        degree = rad.getText()+"";
        //kiểm tra sở thích
        String hobby = "";
        if(chkRNews.isChecked()){
            hobby += chkRNews.getText()+"\n";
        }
        if(chkRBook.isChecked()){
            hobby += chkRBook.getText()+"\n";
        }
        if(chkRCod.isChecked()){
            hobby += chkRCod.getText()+"\n";
        }
        String add = edtAdd.getText()+"";
        //Tạo Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Personal Information");
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //Tạo nội dung
        String msg = name+"\n";
        msg += idC+"\n";
        msg += degree+"\n";
        msg += hobby+"\n";
        msg += "-------------------------------------\n";
        msg += "Additional Information:\n";
        msg += add+"\n";
        msg += "-------------------------------------\n";
        builder.setMessage(msg);//thiết lập nội dung
        builder.create().show();//hiển thị dialog
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(android.R.drawable.ic_dialog_info);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}