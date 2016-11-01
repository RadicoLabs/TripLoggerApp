package com.apm015.android.triplog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

public class TripActivity extends SingleFragmentActivity {

    private static final String EXTRA_TRIP_ID = "com.apm015.android.triplog.trip_id";

    public static Intent newIntent(Context packageContext, UUID tripId) {
        Intent intent = new Intent(packageContext, TripActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, tripId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID tripId = (UUID) getIntent().getSerializableExtra(EXTRA_TRIP_ID);
        return TripFragment.newInstance(tripId);
    }


}
