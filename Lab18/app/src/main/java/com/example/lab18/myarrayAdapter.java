package com.example.lab18;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutId;

    public myarrayAdapter( Activity context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        LayoutId = layoutId;
        this.myArray = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        final Item myItem = myArray.get(position);
        final TextView title = (TextView) convertView.findViewById(R.id.txtTitle);
        title.setText(myItem.getTitle());
        final TextView id = (TextView) convertView.findViewById(R.id.txtID);
        id.setText(myItem.getId());
        final ImageView btnlike = (ImageView) convertView.findViewById(R.id.btnLike);
        final ImageView btnunlike = (ImageView) convertView.findViewById(R.id.btnUnlike);
        int like = myItem.getLike();

        //xử lý hiển thị cho ImageButton btnlike và btnunlike
        if(like==0){
            btnlike.setVisibility(View.INVISIBLE); //cho ẩn btnlike
            btnunlike.setVisibility(View.VISIBLE); //cho hiển thị btnunlike
        }else{
            btnunlike.setVisibility(View.INVISIBLE);
            btnlike.setVisibility(View.VISIBLE);
        }

        //xử lý sự kiện khi click vào image button btnlike và btnunlike
        //cập nhật trạng thái thích vào csdl; thiết lập imagebutton cho phù hợp
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{id.getText().toString()});
//                btnlike.setVisibility(View.INVISIBLE);
//                btnunlike.setVisibility(View.VISIBLE);
                myItem.setLike(0);  // update object state
                notifyDataSetChanged();  // refresh list view
            }
        });
        btnunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{id.getText().toString()});
//                btnunlike.setVisibility(View.INVISIBLE);
//                btnlike.setVisibility(View.VISIBLE);
                myItem.setLike(1);  // update object state
                notifyDataSetChanged();
            }
        });
        //xử lý sự kiện khi click vào mỗi dòng tiêu đề bài hát trên listview
        //chuyển textview title, id sang màu đỏ
        //khai báo intent, bundle, lấy id truyền qua subactivity và gọi activitysub (activitysub xd ở bc sau)
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setTextColor(Color.RED);
                id.setTextColor(Color.RED);
                Intent intent1 = new Intent(context, SubActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("id", id.getText().toString());
                intent1.putExtra("package", bundle1);
                context.startActivity(intent1);
            }
        });
        return  convertView;
    }
    
}
