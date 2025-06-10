package com.example.lab17;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    String DATABASE_NAME = "qlsach.db";

    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processCopy();

        try {
            database = SQLiteDatabase.openDatabase(getDatabaseFullPath(), null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Toast.makeText(this, "Error opening DB: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return;
        }

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Kiểm tra xem bảng tbsach có tồn tại không
        Cursor checkTable = database.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='tbsach'", null);
        if (checkTable.getCount() == 0) {
            Toast.makeText(this, "Table tbsach không tồn tại trong database!", Toast.LENGTH_LONG).show();
            checkTable.close();
            return;
        }
        checkTable.close();

        Cursor c = database.query("tbsach", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int colMasach = c.getColumnIndex("masach");
            int colTensach = c.getColumnIndex("tensach");
            int colGiatien = c.getColumnIndex("giatien");
            do {
                String masach = colMasach != -1 ? c.getString(colMasach) : "null";
                String tensach = colTensach != -1 ? c.getString(colTensach) : "null";
                String giatien = colGiatien != -1 ? c.getString(colGiatien) : "null";

                mylist.add(masach + " - " + tensach + " - " + giatien);
            } while (c.moveToNext());
        }
        c.close();

        myadapter.notifyDataSetChanged();
    }


    private void processCopy() {
        File dbFile = getDatabaseFile();
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAssets();
                Toast.makeText(this, "Copy success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Copy failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }

    private File getDatabaseFile() {
        return new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME);
    }

    private String getDatabaseFullPath() {
        return getDatabaseFile().getPath();
    }

    private void copyDatabaseFromAssets() throws IOException {
        InputStream myInput = getAssets().open(DATABASE_NAME);

        File dbDir = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        OutputStream myOutput = new FileOutputStream(getDatabaseFullPath());

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
