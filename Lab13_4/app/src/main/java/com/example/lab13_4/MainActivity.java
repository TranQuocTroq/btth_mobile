package com.example.lab13_4;
//Custom Listview
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //khai báo listview
    String namephone[] = {"Điện thoại Iphone 12", "Điện thoại Samsung S20", "Điện thoại Nokia 6", "Điện yhoaij Bphone 2020", "Điện thoại Oppo 5", "Điện thoại VSmart joy2"};
    int imagephone[] = {R.drawable.ip, R.drawable.samsung, R.drawable.htc, R.drawable.lg, R.drawable.wp, R.drawable.sky};
    ArrayList<phone> mylist; //khai báo mảng chính
    MyArrayAdapter myadapter; //khai báo adapter
    ListView lv; //khai báo listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        for(int i=0; i< namephone.length; i++){
            mylist.add(new phone(namephone[i], imagephone[i]));
        }
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv.setAdapter(myadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myintent = new Intent(MainActivity.this, SubActivity.class);
                myintent.putExtra("name", namephone[position]);
                startActivity(myintent);
            }
        });
    }
}