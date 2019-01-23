package com.example.amin.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.rtp.AudioStream;
import android.util.Log;

import com.example.amin.beatbox.model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUND_FOLDER  = "sample_sounds";
    private static final int MAX_STREAM = 5;

    private List<Sound> mSounds = new ArrayList<>();
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;

    public List<Sound> getSounds() {
        return mSounds;
    }

    public BeatBox(Context context) {
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
        loadSound();
    }

    private void loadSound() {
        try {
            String[] fileNames =  mAssetManager.list(SOUND_FOLDER);
            for (String fileName : fileNames) {
                String assetPath = SOUND_FOLDER + File.separator + fileName;
                Sound sound = new Sound(assetPath);
                mSounds.add(sound);

                AssetFileDescriptor afd = mAssetManager.openFd(sound.getAssetPath());
                int soundId = mSoundPool.load(afd, 1);
                sound.setSoundId(soundId);
            }
        } catch (IOException e) {
            Log.e(TAG, "file cannot be loaded", e);
        }
    }

    public void play(Sound sound) {
        if (sound.getSoundId() == null)
            return;

        mSoundPool.play(
                sound.getSoundId(),
                1.0f,
                1.0f,
                1,
                0,
                1.0f);
    }

    public void release() {
        mSoundPool.release();
    }
}














