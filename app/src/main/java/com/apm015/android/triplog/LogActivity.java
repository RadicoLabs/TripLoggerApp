package com.apm015.android.triplog;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Adam on 24/10/2016.
 */
public class LogActivity  extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LogActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return LogFragment.newInstance();
    }

}
