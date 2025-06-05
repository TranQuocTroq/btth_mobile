package com.example.lab13_1;
//Auto Complete TextView Activity
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView selection;
    //khai báo 2 completetextview
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;
    //khởi tạo mảng tạm để Test
    String arr[] = {"Hà Nội", "Huế", "Sài Gòn", "Hà Giang", "Hội An", "Kiên Giang", "Lâm Đồng", "Long Khánh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        //lấy dtg autopletetextview
        singleComplete = (AutoCompleteTextView) findViewById(R.id.editauto);
        ArrayAdapter myadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        //thiết lập arrayadapter
        singleComplete.setAdapter(myadapter);

        //lấy dtg multiautocompletetextview ra
        multiComplete = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
        //thiết lập ArrayAdapter
        multiComplete.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1));

        //đôi với multiautocompletetextview bắt buộc phải gọi dòng lệnh naày
        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}