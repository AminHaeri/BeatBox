package com.example.amin.beatbox;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * All activities that host one fragment must extend this class
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentById(R.id.fragment_list_container) == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_list_container, createFragment())
                    .commit();
        }
    }
}
