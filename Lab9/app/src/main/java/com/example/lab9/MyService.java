package com.example.lab9;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer mymedia;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //goi ham onCreate de tao dtg ma service quan ly
    @Override
    public  void onCreate(){
        super.onCreate();
        mymedia = MediaPlayer.create(MyService.this, R.raw.tinhme);
        mymedia.setLooping(true); //cho phep lap lai lien tuc
    }
    //goi ham onStartCommand de khoi tao chay doi tuong ma Service quan ly
    @Override
    public  int onStartCommand(Intent intent, int flags, int startId){
        if(mymedia.isPlaying())
            mymedia.pause();
        else
            mymedia.start();
        return super.onStartCommand(intent, flags, startId);
    }
    //goi ham onDestroy de dung dtg ma service quan ly
    @Override
    public  void onDestroy(){
        super.onDestroy();
        mymedia.stop();
    }
}