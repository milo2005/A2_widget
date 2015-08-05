package com.example.dariofernando.musicapp.service;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;

/**
 * Created by DarioFernando on 27/07/2015.
 */
public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    public static final String ACTION_PLAY="com.example.dariofernando.musicapp.play";
    public static final String ACTION_PAUSE="com.example.dariofernando.musicapp.pause";
    public static final String ACTION_STOP="com.example.dariofernando.musicapp.stop";

    MediaPlayer player;
    boolean paused;


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
        if(paused){
            paused = false;
            player.start();
        }else{
            createMediaPlayer();
            AssetFileDescriptor assetFileDescriptor;

            AssetManager assetManager = getAssets();
            try {
                assetFileDescriptor = assetManager.openFd("musica/wtm.mp3");
                long startOffset = assetFileDescriptor.getStartOffset();
                long endOffset = assetFileDescriptor.getLength();
                player.setDataSource(assetFileDescriptor.getFileDescriptor()
                        ,startOffset,endOffset);
                player.prepareAsync();
                startServiceForeground();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void pauseSong() {
        paused = true;
        player.pause();
    }

    private void stopSong() {
        player.stop();
        player.release();
        player = null;
        stopSelf();
    }

    @Override
    public void onDestroy() {
        stopServiceForeground();
        super.onDestroy();

    }

    private void createMediaPlayer() {
        player = new MediaPlayer();
        player.setOnPreparedListener(this);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnCompletionListener(this);

    }
    //endregion

    //region Foreground Methods
    public void startServiceForeground(){
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Reproduciendo")
                .setContentText("Cancion")
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

    @Override
    public void onPrepared(MediaPlayer mp) {
        player.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSong();
    }
}
