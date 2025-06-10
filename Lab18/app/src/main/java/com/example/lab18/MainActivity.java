package com.example.lab18;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME = "arirang.sqlite";
    EditText edtsearch;
    ListView lv1, lv2, lv3;
    ArrayList<Item> list1, list2, list3;
    myarrayAdapter myarray1, myarray2, myarray3;
    TabHost tab;
    ImageButton btndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy(); //copy csdl arirang.sqlite
        //mở csdl đã copy lưu vào biến database
        database = openOrCreateDatabase("arirang.sqlite", MODE_PRIVATE, null);
        addControl(); //hàm thêm các controls
        addSearch(); //xử lý các cvc tìm kiếm
        addEvents(); //xử lý sự kiện khi chuyển Tab và các sự kiện khác
    }

    //hàm khai báo và add các controls vào giao diện
    //trên 3tab chúng ta có 3 listview ứng với 3 dsach dl (dl tìm kiếm, ds bài hát gốc, ds bài hát yêu thích) và adapter riêng
    private void addControl(){
        btndelete = (ImageButton) findViewById(R.id.btnDelete);
        tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.search));
        tab.addTab(tab1);
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.list));
        tab.addTab(tab2);
        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.favourite));
        tab.addTab(tab3);

        edtsearch = (EditText) findViewById(R.id.edtSearch);
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);
        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();
        myarray1 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list1);
        myarray2 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list2);
        myarray3 = new myarrayAdapter(MainActivity.this, R.layout.listitem, list3);
        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    //xử lý sự kiện khi chuyển qua lại giữa các Tab danh sách và yêu thích
    private void addEvents(){
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t2")){
                    addDanhsach();
                }
                if(tabId.equalsIgnoreCase("t3")){
                    addYeuthich();
                }
            }
        });

        //sự kie kiện khi click vào btndelete trên tab search
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtsearch.setText("");
            }
        });
    }

    //hàm thêm bài hát vào ListView trên tab Yêu thích
    private void addYeuthich(){
        myarray3.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE YEUTHICH = 1", null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            list3.add(new Item(c.getString(0), c.getString(1), c.getInt(4)));
            c.moveToNext();
        }
        c.close();
        myarray3.notifyDataSetChanged();
    }

    //hàm thêm bài hát vào listview trên tab ds bài hát
    private void addDanhsach(){
        myarray2.clear();
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList",null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            list2.add(new Item(c.getString(0), c.getString(1), c.getInt(4)));
            c.moveToNext();
        }
        c.close();
        myarray2.notifyDataSetChanged();
    }

    //hàm xử lý tìm kiếm bài hát theo title và id
    private void addSearch(){
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getdata();
            }
            private void getdata(){
                String dulieunhap = edtsearch.getText().toString();
                list1.clear();
                if(!edtsearch.getText().toString().equals("")){
                    Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE TENBH1 LIKE '"+"%"+dulieunhap+"%"+"' OR MABH LIKE '"+"%"+dulieunhap+"%"+"'", null);
                    c.moveToFirst();
                    while (c.isAfterLast()==false){
                        list1.add(new Item(c.getString(0), c.getString(1), c.getInt(4)));
                        c.moveToNext();
                    }
                    c.close();
                }
                myarray1.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //hàm xử lý copy csdl từ thư mục assets vào hệ thognos thư mục cài đặt
    private void processCopy(){
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private String getDatabasePath(){
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX+DATABASE_NAME;
    }
    public void CopyDataBaseFromAsset(){
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!f.exists())
                f.mkdir();
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length=myInput.read(buffer))>0){
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
        }catch(IOException e) {
                e.printStackTrace();
        }
    }
}