package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that organizes the Sound objects
 *
 * Created by Rudolf on 3/8/2016.
 */
public class BeatBox {

    // TAG for logging BeatBox
    private static final String TAG = "BeatBox";

    // Assets location
    private static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssets;           // Assets accessor. Accessible from any context.
    private List<Sound> mSounds;            // List of Sounds

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSounds = new ArrayList<>();

        loadSounds();
    }

    /**
     * Looks in assets using list(String)
     *
     * If not found, throws IOException
     */
    private void loadSounds() {

        String[] soundNames;

        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException exception) {
            Log.e(TAG, "Could not list assets", exception);
            return;
        }

        // Convert sound names to their asset paths and
        // add to mSounds via Sound constructor
        for (String filename : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }

    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
