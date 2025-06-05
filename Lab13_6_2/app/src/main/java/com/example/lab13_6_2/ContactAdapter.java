package com.example.lab13_6_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> contacts;

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView txtName, txtPhone;
        ImageButton btnCall, btnSMS, btnDetails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
            holder = new ViewHolder();
            holder.txtName = convertView.findViewById(R.id.txtName);
            holder.txtPhone = convertView.findViewById(R.id.txtPhone);
            holder.btnCall = convertView.findViewById(R.id.btnCall);
            holder.btnSMS = convertView.findViewById(R.id.btnSMS);
            holder.btnDetails = convertView.findViewById(R.id.btnDetails);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact c = contacts.get(position);
        holder.txtName.setText((position + 1) + "-" + c.name);
        holder.txtPhone.setText(c.phone);

        holder.btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + c.phone));
            context.startActivity(intent);
        });

        holder.btnSMS.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + c.phone));
            context.startActivity(intent);
        });

        holder.btnDetails.setOnClickListener(v -> {
            Toast.makeText(context, "Chi tiết của " + c.name, Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}


//đây là class quan trọng nhất, mới nhất dùng để custom layout

