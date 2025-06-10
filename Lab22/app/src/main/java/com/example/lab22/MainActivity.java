package com.example.lab22;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv1;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist;
    String URL = "https://www.w3schools.com/xml/simple.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnparse = findViewById(R.id.btnparse);
        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv1.setAdapter(myadapter);
        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadExampleTask task = new LoadExampleTask();
                task.execute();
            }
        });
    }
    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<String>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);

                if (xml == null || xml.isEmpty()) {
                    Log.e("ParseXML", "XML is null or empty!");
                    return null;
                }

                parser.setInput(new StringReader(xml));
                int eventType = -1;
                String nodeName;
                String datashow = "";
                String name = "";
                String price = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    eventType = parser.next();

                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("name")) {
                                name = parser.nextText();
                            } else if (nodeName.equals("price")) {
                                price = parser.nextText();
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("food")) {
                                datashow = name + " - " + price;
                                list.add(datashow);
                                name = "";
                                price = "";
                                datashow = "";
                            }
                            break;
                    }
                }
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            if (result == null) {
                Toast.makeText(MainActivity.this, "Lỗi khi tải XML!", Toast.LENGTH_SHORT).show();
                return;
            }
            myadapter.clear();
            myadapter.addAll(result);
            myadapter.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}