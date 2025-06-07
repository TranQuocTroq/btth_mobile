package com.example.lab15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtClassID, edtClassname, edtNumber;
    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtClassID = findViewById(R.id.edtClassID);
        edtClassname = findViewById(R.id.edtClassname);
        edtNumber = findViewById(R.id.edtNumber);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);

        //Tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        //Tạo và mở csdl sqlite
        mydatabase = openOrCreateDatabase("studentmanagement.db", MODE_PRIVATE, null);
        //Tạo table để chứa dl
        try {
            String sql = "CREATE TABLE tbclass(classid TEXT primary key, classname TEXT, number INTEGER)";
            mydatabase.execSQL(sql);
        }catch (Exception e){
            Log.e("Error", "Table đã tồn tại");
        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classid = edtClassID.getText().toString();
                String classname = edtClassname.getText().toString();
                int number = Integer.parseInt(edtNumber.getText().toString());

                ContentValues myvalue = new ContentValues();
                myvalue.put("classid", classid);
                myvalue.put("classname", classname);
                myvalue.put("number", number);

                String msg = "";
                if(mydatabase.insert("tbclass", null, myvalue)==-1) {
                    msg = "Fail to Insert Record";
                }else {
                    msg = "Insert record Successfully";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classid = edtClassID.getText().toString();
                int n = mydatabase.delete("tbclass", "classid=?", new String[]{classid});

                String msg = "";
                if(n==0) {
                    msg = "No record to Delete";
                }else {
                    msg = n+" record is deleted";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classid = edtClassID.getText().toString();
                int number = Integer.parseInt(edtNumber.getText().toString());

                ContentValues myvalue = new ContentValues();
                myvalue.put("number", number);

                int n = mydatabase.update("tbclass", myvalue, "classid=?", new String[]{classid});
                String msg = "";
                if(n==0) {
                    msg = "No record to Update";
                }else {
                    msg = n+" record is updated";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mylist.clear();
                Cursor c = mydatabase.query("tbclass", null, null, null, null, null, null);
                c.moveToNext();
                String data = "";
                while (c.isAfterLast()==false){
                    data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
                    c.moveToNext();
                    mylist.add(data);
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });
    }
}