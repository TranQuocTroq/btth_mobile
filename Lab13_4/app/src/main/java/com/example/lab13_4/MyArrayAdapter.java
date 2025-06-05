package com.example.lab13_4;

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

public class MyArrayAdapter extends ArrayAdapter<phone> {
    Activity context;
    int idlayout;
    ArrayList<phone> mylist;
    //tạo constructor để MainACtivity gọi đến vfa truyền các tham số
    public MyArrayAdapter(Activity context, int idlayout, ArrayList<phone> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        this.idlayout = idlayout;
        this.mylist = mylist;
    }

    //goọi đến hàm getview để xây dựng lại Adapter
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView = myInflactor.inflate(idlayout, null);
        phone myphone = mylist.get(position);
        //ứng với môix thuộc tính, ta thực hiện 2 việc
        //gán id
        ImageView imgphone = convertView.findViewById(R.id.imgPhone);
        //thiết lập dl
        imgphone.setImageResource(myphone.getImagephone());
        //textview
        TextView txtnamphone = convertView.findViewById(R.id.txtNamephone);
        txtnamphone.setText(myphone.getNamephone());
        return convertView;
    }
}
//đây là class quan trọng nhất, mới nhất dùng để custom layout
