package com.example.lab20;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    //khai báo Activity để lưu trữ context của MainActivity
    Activity contextCha;
    //constructor này được truyền vào là MainActivity

    public MyAsyncTask(Activity contextCha) {
        this.contextCha = contextCha;
    }
    //hàm này sẽ được thc hiện đầu tiên

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }
    //sau đó tới hàm doInBackground
    //tuyệt đối không được cập nhật giao diện trong hàm này

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i=0; i<=100; i++){
            //nghỉ 100 milisecond thì tiến hành update UI
            SystemClock.sleep(100);
            //khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }
    //ta cập nhật giao diện trong hàm này

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        //thông qua contextCha để lấy được control trong MainActivity
        ProgressBar paCha = (ProgressBar) contextCha.findViewById(R.id.progressBar1);
        //vì publishProgress chỉ truyền 1 đối số nên mảng values chỉ có 1 ptu
        int giatri = values[0];
        //tăng giá trị của Progressbar lên
        paCha.setProgress(giatri);
        //đồng thời hiển thị giá trị là % lên TextView
        TextView txtmsg = (TextView) contextCha.findViewById(R.id.textView);
        txtmsg.setText(giatri+"%");
    }
    //sau khi tiến trình thực hiện xong thì hàm này xảy ra

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Toast.makeText(contextCha, "Update xong roi do", Toast.LENGTH_LONG).show();
    }
}
