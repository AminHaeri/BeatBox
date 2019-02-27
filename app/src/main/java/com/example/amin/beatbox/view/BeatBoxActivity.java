package com.example.amin.beatbox.view;

import android.net.Uri;
import android.support.v4.app.Fragment;

public class BeatBoxActivity extends SingleFragmentActivity implements BeatBoxFragment.OnFragmentInteractionListener {

    @Override
    public Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
