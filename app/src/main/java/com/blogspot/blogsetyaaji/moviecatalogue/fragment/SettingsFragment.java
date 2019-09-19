package com.blogspot.blogsetyaaji.moviecatalogue.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.blogsetyaaji.moviecatalogue.R;
import com.blogspot.blogsetyaaji.moviecatalogue.alarm.ReleaseAlarm;
import com.blogspot.blogsetyaaji.moviecatalogue.model.movie.MovieItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    private ReleaseAlarm releaseAlarm = new ReleaseAlarm();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference languafePreference = findPreference("language");
        SwitchPreferenceCompat dailyReminder = findPreference("daily_reminder");
        SwitchPreferenceCompat releaseReminder = findPreference("relase_reminder");

        dailyReminder.setOnPreferenceChangeListener(this);
        releaseReminder.setOnPreferenceChangeListener(this);

        languafePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                return true;
            }
        });
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        boolean isSet = (boolean) newValue;

        if (key.equals("daily_reminder")) {
            if (isSet) {

            } else {

            }
        } else {
            if (isSet) {
                releaseAlarm.setReapeatAlarm(getActivity());
            } else {
                releaseAlarm.cancelReleaseAlarm(getActivity());
            }
        }

        return true;
    }
}