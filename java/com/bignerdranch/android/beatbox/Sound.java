package com.bignerdranch.android.beatbox;

/**
 * Created by Rudolf on 3/8/2016.
 */
public class Sound {

    private String mAssetPath;          // String Path
    private String mName;               // Name of Sound
    private Integer mSoundId;           // Sound Integer ID

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];        // Last String in path
        mName = filename.replace(".wav", "");                       // Removes .wav
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }
}
