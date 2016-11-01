package com.apm015.android.triplog;

import android.support.v4.app.Fragment;

/**
 * Created by Adam on 9/10/2016.
 */
public class TripListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TripListFragment();
    }



}
