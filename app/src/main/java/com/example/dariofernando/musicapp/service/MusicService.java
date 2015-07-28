package com.example.dariofernando.musicapp.service;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * Created by DarioFernando on 27/07/2015.
 */
public class MusicService extends Service {

    public static final String ACTION_PLAY="com.example.dariofernando.musicapp.play";
    public static final String ACTION_PAUSE="com.example.dariofernando.musicapp.pause";
    public static final String ACTION_STOP="com.example.dariofernando.musicapp.stop";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent.getAction().equals(ACTION_PLAY)){
            playSong();
        }else if(intent.getAction().equals(ACTION_PAUSE)){
            pauseSong();
        }else{
            stopSong();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    //region Music Control
    private void playSong() {
    }

    private void pauseSong() {
    }

    private void stopSong() {
    }
    //endregion

    //region Foreground Methods
    public void startServiceForeground(){
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Reproduciendo")
                .setContentText("Revelations")
                .setSmallIcon(android.R.drawable.ic_menu_edit)
                .build();

        startForeground(101, notification);
    }

    public void stopServiceForeground(){
        stopForeground(true);
    }
    //endregion

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
