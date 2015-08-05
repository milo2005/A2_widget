package com.example.dariofernando.musicapp.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.dariofernando.musicapp.R;
import com.example.dariofernando.musicapp.service.MusicService;

/**
 * Implementation of App Widget functionality.
 */
public class MusicWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Intent intentPlay = new Intent(MusicService.ACTION_PLAY);
        Intent intentPause = new Intent(MusicService.ACTION_PAUSE);
        Intent intentStop = new Intent(MusicService.ACTION_STOP);

        PendingIntent pPlay = PendingIntent.getBroadcast(context,101, intentPlay
                ,PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pPause = PendingIntent.getBroadcast(context,102, intentPause
                ,PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pStop = PendingIntent.getBroadcast(context,103, intentStop
                ,PendingIntent.FLAG_UPDATE_CURRENT);


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.music_widget);
        views.setOnClickPendingIntent(R.id.play, pPlay);
        views.setOnClickPendingIntent(R.id.pause, pPause);
        views.setOnClickPendingIntent(R.id.stop, pStop);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

