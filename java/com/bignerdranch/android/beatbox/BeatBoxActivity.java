package com.bignerdranch.android.beatbox;

import android.support.v4.app.Fragment;

/**
 * Created by Rudolf on 3/8/2016.
 */
public class BeatBoxActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }
}
