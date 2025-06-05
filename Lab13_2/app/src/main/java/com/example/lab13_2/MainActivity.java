package com.example.lab13_2;
//Gridview Activity
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //dùng mảng 1 chiều hoặc ArrayList để lưu 1 số dũ liệu
    String arr[] = {"Ipad", "Iphone", "New Ipad", "Samsung", "Nokia", "Sony Ericson", "LG", "Q-Mobile", "HTC", "Blackberry", "G Phone", "FPT - Phone", "HK Phone"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //dtg này dùng để hiển thị ptu được họn trong gridview
        final TextView selection = (TextView) findViewById(R.id.selection);
        final GridView gv = (GridView) findViewById(R.id.gridView1);
        //gán datasource vào arrayAdapter
        ArrayAdapter<String> da = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        //gán datasource vào gridview
        gv.setAdapter(da);
        //thiết lập sự kiện cho gridview
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3) {
                //hiển thị ptu được chọn trong gridview lên textview
                selection.setText(arr[arg2]);
            }
        });
    }
}