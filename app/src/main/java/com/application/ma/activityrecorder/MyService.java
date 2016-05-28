package com.application.ma.activityrecorder;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    private final IBinder myBinder = new MyLocalBinder();


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    public String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (df.format(new Date()));
    }

    public String getCurrentYear() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.US);
        return (df.format(new Date()));
    }

    public String getCurrentMonth() {
        SimpleDateFormat df = new SimpleDateFormat("MM", Locale.US);
        return (df.format(new Date()));
    }

    public String getDayInMonth() {
        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.US);
        return (df.format(new Date()));
    }

    public class MyLocalBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
