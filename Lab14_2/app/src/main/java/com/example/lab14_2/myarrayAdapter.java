package com.example.lab14_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutId;
    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item>arr){
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        Item myItem = myArray.get(position);
        ImageView btnlike = convertView.findViewById(R.id.btnLike);
        int like = myItem.getLike();
        if(like==1){
            btnlike.setImageResource(R.drawable.like);
        }else{
            btnlike.setImageResource(R.drawable.unlike);
        }
        TextView title = convertView.findViewById(R.id.txtTitle);
        title.setText(myItem.getTitle());
        TextView code = convertView.findViewById(R.id.txtCode);
        code.setText(myItem.getCode());
        return convertView;
    }
}
