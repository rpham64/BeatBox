package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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

    // Max # of Sounds playable
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;           // Assets accessor. Accessible from any context.
    private List<Sound> mSounds;            // List of Sounds
    private SoundPool mSoundPool;           // Controls max # of sounds playing

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSounds = new ArrayList<>();

        // Old constructor that's deprecated, but needed for compatibility
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);

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

            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);            // Load Sound into SoundPool
                mSounds.add(sound);     // Add Sound to mSounds list
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound " + filename, ioe);
            }

        }

    }

    /**
     * Loads a Sound into SoundPool
     *
     * @param sound
     * @throws IOException
     */
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(assetFileDescriptor, 1);
        sound.setSoundId(soundId);
    }

    /**
     * Play Sound in SoundPool
     *
     * @param sound
     */
    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();

        if (soundId == null) return;

        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    /**
     * Releases all memory and native resources used by the SoundPool object
     */
    public void release() {
        mSoundPool.release();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
