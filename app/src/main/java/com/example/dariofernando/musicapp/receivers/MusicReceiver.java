package com.example.dariofernando.musicapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.dariofernando.musicapp.service.MusicService;

/**
 * Created by DarioFernando on 27/07/2015.
 */
public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentCommand = new Intent(context, MusicService.class);
        intentCommand.setAction(intent.getAction());
        context.startService(intentCommand);

    }
}
