package com.example.amin.beatbox.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.amin.beatbox.model.BeatBox;
import com.example.amin.beatbox.model.Sound;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }

    public void setBeatBox(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }
}
