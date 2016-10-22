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
 * Created by rajatpunkstaa on 11/10/16.
 */

public class BeatBox {
    // This class controls the sample sounds and the functions that are done by them.
    // Okay Now! Lets Go !!

    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    public static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;



    // The constructor
    public BeatBox(Context context){

        mAssets = context.getAssets();
        // Using SoundPool in the constructor
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }
    // A method to load all the sounds in the app and list out them



    private void loadSounds(){
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found" + soundNames.length + "sounds");
        }catch (IOException ioe){
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
        //Building up a list of sounds
        for (String filename : soundNames){
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe){
                Log.e(TAG, "Could not load sound" + filename, ioe);
            }
        }

    }
    //Play sound method
    public void play(Sound sound){
        Integer soundId = sound.getSoundId();
        if(soundId == null){
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);

    }
    public void release(){
        mSoundPool.release();
    }
    private void load(Sound sound) throws IOException{
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetpath());
        int soundId = mSoundPool.load(afd,1);
        sound.setSoundId(soundId);

    }
    public List<Sound> getSounds() {
        return mSounds;
    }

}
