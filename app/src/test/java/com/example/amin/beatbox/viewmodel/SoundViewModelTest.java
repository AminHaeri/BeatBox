package com.example.amin.beatbox.viewmodel;

import com.example.amin.beatbox.model.BeatBox;
import com.example.amin.beatbox.model.Sound;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SoundViewModelTest {

    private SoundViewModel mSubject;
    private Sound mSound;
    private BeatBox mBeatBox;

    @Before
    public void setUp() throws Exception {
        mBeatBox = Mockito.mock(BeatBox.class);
        mSubject = new SoundViewModel(mBeatBox);

        mSound = Mockito.mock(Sound.class);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle() {
        assertThat(mSound.getName(), is(mSubject.getTitle()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked() {
        mSubject.onButtonClicked();

        Mockito.verify(mBeatBox).play(mSound);
    }

}