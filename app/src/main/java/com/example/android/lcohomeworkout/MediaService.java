package com.example.android.lcohomeworkout;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MediaService extends Service {
    private LocalBinder binder;
    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
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
        stopMedia();
        return super.onUnbind(intent);
    }

    public void playMedia() {
        mediaPlayer.start();
    }

    public void pauseMedia() {
        mediaPlayer.pause();
    }

    public void stopMedia() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public class LocalBinder extends Binder {
        public MediaService getService() {
            return MediaService.this;
        }
    }
}
