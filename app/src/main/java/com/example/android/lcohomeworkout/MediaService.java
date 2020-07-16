package com.example.android.lcohomeworkout;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;

public class MediaService extends Service {
    private static final String TAG = "MediaService";

    private LocalBinder binder;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called");

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.joined_audio);
        }
        if (binder == null) {
            binder = new LocalBinder();
        }
        playMedia();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: called");

        stopMedia();
        return super.onUnbind(intent);
    }

    public void playMedia() {
        if (mediaPlayer != null)
            mediaPlayer.start();
    }

    public void pauseMedia() {
        Log.d(TAG, "pauseMedia: MediaPlayer is null but method is called");
        /* not null check are really important in media controls */
        if (mediaPlayer != null)
            mediaPlayer.pause();
    }

    public void stopMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public class LocalBinder extends Binder {
        public MediaService getService() {
            return MediaService.this;
        }
    }
}
