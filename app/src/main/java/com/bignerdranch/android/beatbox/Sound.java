package com.bignerdranch.android.beatbox;

/**
 * Created by rajatpunkstaa on 11/10/16.
 */

public class Sound {
    // This class is to control the filename of the sounds, the name the user sees and the sounds that get played.
    // This class will have  an object that will be responsible for the filenames and the playback of the sounds.
    private String mAssetpath;
    private String mName;
    private Integer mSoundId;

    //The constructor
    public Sound(String assetPath){
        mAssetpath = assetPath;
        String[] components = mAssetpath.split("/");
        String filename = components[components.length -1];
        mName = filename.replace(".wav","");

    }

    public String getAssetpath() {
        return mAssetpath;
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
