package com.apm015.android.triplog;


import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;

import java.util.UUID;

/**
 * Created by Adam on 23/10/2016.
 */
public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

}