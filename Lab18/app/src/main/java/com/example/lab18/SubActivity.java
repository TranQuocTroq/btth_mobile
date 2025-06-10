package com.example.lab18;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {
    TextView txtid, txtsongname, txtlyrics, txtmusician;
    ImageButton btnlike, btnunlike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        txtid = (TextView) findViewById(R.id.txtID);
        txtsongname = (TextView) findViewById(R.id.txtSongname);
        txtlyrics = (TextView) findViewById(R.id.txtLyrics);
        txtmusician = (TextView) findViewById(R.id.txtMusician);
        btnlike = (ImageButton) findViewById(R.id.btnLike);
        btnunlike = (ImageButton) findViewById(R.id.btnUnlike);

        //Nhận Intent từ myarrayAdapter, lấy dl khỏi Bundle
        Intent callerIntent1 = getIntent();
        Bundle backagecaller1 = callerIntent1.getBundleExtra("package");
        String id = backagecaller1.getString("id");

        //truy vấn dl từ id nhận được; hiển thị dl id, songname, lyrics, musician, trạng thái like lên activitysub
        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE '"+id+"'", null);
        txtid.setText(id);
        c.moveToFirst();
        txtsongname.setText(c.getString(1));
        txtlyrics.setText(c.getString(2));
        txtmusician.setText(c.getString(4));
        if(c.getInt(4) ==0){
            btnlike.setVisibility(View.INVISIBLE);
            btnunlike.setVisibility(View.VISIBLE);
        }else{
            btnunlike.setVisibility(View.INVISIBLE);
            btnlike.setVisibility(View.VISIBLE);
        }
        c.close();

        //xử lý sự kiện khi click vào button unlike và btnlike
        //cập nhật dl vào csdl, thay đổi trạng thái hiển thị cho btnlike vfa btnunlike
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtid.getText().toString()});
                btnlike.setVisibility(View.INVISIBLE);
                btnunlike.setVisibility(View.VISIBLE);
            }
        });
        btnunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtid.getText().toString()});
                btnunlike.setVisibility(View.INVISIBLE);
                btnlike.setVisibility(View.VISIBLE);
            }
        });
    }
}