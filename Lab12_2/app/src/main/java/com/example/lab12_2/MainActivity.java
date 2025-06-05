package com.example.lab12_2;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.widget.AdapterView;


public class MainActivity extends AppCompatActivity {
    //khai báo biến
    ListView lv;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> arrAdapter;
    EditText edtwork, edthour, edtmi;
    TextView txtdate;
    Button btnwork;

    SharedPreferences sharedPreferences;
    final String PREF_NAME = "MyPrefs";
    final String TASK_LIST_KEY = "TaskList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edthour = findViewById(R.id.edtHour);
        edtmi = findViewById(R.id.edtMi);
        edtwork = findViewById(R.id.edtWork);
        btnwork = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtDate);
        //ở đây chúng ta không sử dụng dl cố định, mà dl của Listview được cập nht trong qus trinhg chạy, nên ở đây ta
        //khai bảo mảng ArrayList kiểu String rỗng
        arrayWork = new ArrayList<>();
        //khai báo ArrayAdapter, đưa mảng dl vào ArrayAdapter
        arrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        //đưa Adapter có dl lên Listview
        lv.setAdapter(arrAdapter);

        // SharedPreferences
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Load saved tasks
        loadTasks();
        //lấy ngày giờ hệ thống
        Date currentDate = Calendar.getInstance().getTime();
        //format theo định dạng dd/mm/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        //hiển thị lên textview
        txtdate.setText("Hôm nay: "+simpleDateFormat.format(currentDate));
        //viết pthuc khi click vào button btnwork
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nếu 1 trong 4 edt 0 có nội dung thì hiện lên thông báo bằng hộp thoại dialog
                if(edtwork.getText().toString().equals("")||edthour.getText().toString().equals("")||edtmi.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }
                //lấy nội dung công vieewjc và time ra từ edt và đưa vào mảng arraywork, cập nhật lại Adapter
                else {
                    String str = edtwork.getText().toString() + " - " + edthour.getText().toString()+":"+edtmi.getText().toString();
                    //để thêm dữ liệu cho listview, chúng ta cần 2 bước
                    //cập thêm liệu cho mảng
                    arrayWork.add(str);
                    //xóa
                    //arrayWork.remove(i);
                    //cập nhật lại Adapter
                    arrAdapter.notifyDataSetChanged();
                    edthour.setText("");
                    edtmi.setText("");
                    edtwork.setText("");
                }
            }
        });
        // Xóa công việc khi click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete Task");
                builder.setMessage("Do you want to delete this task?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayWork.remove(position);
                        arrAdapter.notifyDataSetChanged();
                        saveTasks(); // lưu sau khi xóa
                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });
    }
    // Lưu công việc vào SharedPreferences dưới dạng JSON
    private void saveTasks() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        JSONArray jsonArray = new JSONArray(arrayWork);
        editor.putString(TASK_LIST_KEY, jsonArray.toString());
        editor.apply();
    }

    // Tải công việc từ SharedPreferences
    private void loadTasks() {
        String json = sharedPreferences.getString(TASK_LIST_KEY, null);
        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayWork.add(jsonArray.getString(i));
                }
                arrAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}