/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */
package com.corposense.ubs.view.fragment;


// TODO: Auto-generated Javadoc

import android.os.Bundle;

import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;

import com.corposense.ubs.R;

/**
 * Fragment that appears in the "content_frame", shows a animal.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);
    }
}
