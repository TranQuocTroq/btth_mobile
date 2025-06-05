package com.example.lab13_5;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyarrayAdapter extends ArrayAdapter<Image> {
    Activity context = null;
    ArrayList<Image> myArray = null;
    int LayoutId;

    /**
     * Constructor này dùng đ khởi tạo các giá trị từ MainActivity truyền vào
     * @param context: là Activity từ Main
     * @param LayoutId: là layout custom do ta tạo (listitem.xml)
     * @param arr: Danh sách đối tượng truyền từ Main
     */

    public MyarrayAdapter(Activity context, int LayoutId, ArrayList<Image> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        final Image myimage = myArray.get(position);
        //dòng lệnh lấy imageview ra để hiển thị hình ảnh
        final ImageView imgitem = (ImageView) convertView.findViewById(R.id.imageView1);
        imgitem.setImageResource(myimage.getImg());

        //dòng lệnh lấy Textview ra để hiển thị ảnh
        final TextView myname = (TextView) convertView.findViewById(R.id.textView1);
        myname.setText(myimage.getName());
        return convertView;
    }
}

//đây là class quan trọng nhất, mới nhất dùng để custom layout
