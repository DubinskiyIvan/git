package com.example.myapplication;


import android.os.Bundle;
import android.preference.PreferenceFragment;


public class SharedPreferencesFragment extends PreferenceFragment {

    public SharedPreferencesFragment() {

    }

    public static SharedPreferencesFragment newInstance() {
        SharedPreferencesFragment fragment = new SharedPreferencesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.shared);
    }

}
