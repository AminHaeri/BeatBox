package com.example.amin.beatbox;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeatBoxActivity extends SingleFragmentActivity implements BeatBoxFragment.OnFragmentInteractionListener {

    @Override
    public Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
