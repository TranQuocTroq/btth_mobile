package com.example.lab23;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ListView lv1;
    ArrayList<List> mylist;
    MyArrayAdapter myadapter;
    String nodeName;
    String title = "";
    String link = "";
    String description = "";
    String des = "";
    String urlStr = "";
    Bitmap mIcon_val = null;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<List>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);

        lv1.setOnItemClickListener((parent, view, position, id) -> {
            List selectedItem = mylist.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(selectedItem.getLink()));
            startActivity(intent);
        });

        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<List>> {
        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            mylist = new ArrayList<>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();

                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(URL);
                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                boolean insideItem = false;
                title = "";
                link = "";
                description = "";
                des = "";
                urlStr = "";
                mIcon_val = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("item")) {
                                insideItem = true;
                                title = "";
                                link = "";
                                description = "";
                                des = "";
                                urlStr = "";
                                mIcon_val = null;
                            } else if (insideItem) {
                                if (nodeName.equals("title")) {
                                    title = parser.nextText();
                                } else if (nodeName.equals("link")) {
                                    link = parser.nextText();
                                } else if (nodeName.equals("description")) {
                                    description = parser.nextText();
                                    if (description.contains("</br>")) {
                                        des = description.substring(description.indexOf("</br>") + 5);
                                    } else {
                                        des = description;
                                    }
                                } else if (nodeName.equals("enclosure")) {
                                    urlStr = parser.getAttributeValue(null, "url");
                                    if (urlStr != null && !urlStr.isEmpty()) {
                                        try {
                                            URL newurl = new URL(urlStr);
                                            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                }
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("item")) {
                                insideItem = false;
                                mylist.add(new List(mIcon_val, title, des, link));
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return mylist;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
            Toast.makeText(MainActivity.this, "Loaded: " + result.size() + " items", Toast.LENGTH_LONG).show();
        }
    }
}
