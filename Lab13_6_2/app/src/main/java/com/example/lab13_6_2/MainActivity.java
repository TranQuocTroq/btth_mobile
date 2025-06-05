package com.example.lab13_6_2;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        contacts = new ArrayList<>();
        contacts.add(new Contact("Nguyễn Văn Nam", "0932588634"));
        contacts.add(new Contact("Trần Văn Tú", "0932588635"));
        contacts.add(new Contact("Nguyễn Thị Lan", "0932588636"));
        contacts.add(new Contact("Nguyễn Kim Ngân", "0932588637"));

        ContactAdapter adapter = new ContactAdapter(this, contacts);
        listView.setAdapter(adapter);
    }
}
