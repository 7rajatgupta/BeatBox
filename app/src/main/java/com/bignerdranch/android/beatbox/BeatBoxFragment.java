package com.bignerdranch.android.beatbox;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by rajatpunkstaa on 10/10/16.
 */

public class BeatBoxFragment extends Fragment {
    //This is the fragment class for the application

    private BeatBox mBeatBox;
    private Sound mSound;
    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();

    }
    //onCreate
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBeatBox = new BeatBox(getActivity());
        setRetainInstance(true);// Retaining the fragment across rotation and when memory is claimed by the device
        
    }
    //onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);
        // Implementation of the RecyclerView

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //Wiring up the soundAdapter
        recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));
        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeatBox.release(); // release the sounds stack when the activity is destroyed
    }

    //Implementation of the ViewHolder named SoundHolder here
    private class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Button mButton;
        public SoundHolder(LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);


        }
        //Binding to a  sound
        public void bindSound(Sound sound){
            mSound = sound;
            mButton.setText(mSound.getName());
        }
        @Override
        public void onClick(View v){
            mBeatBox.play(mSound);
        }


    }
    //Implementation of the Adapter as SoundAdapter
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{
        private List<Sound> mSounds;



        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SoundHolder(inflater, parent);

        }
        @Override
        public void onBindViewHolder(SoundHolder soundHolder, int position){
            Sound sound = mSounds.get(position);
            soundHolder.bindSound(sound);


        }
        @Override
        public int getItemCount(){
            return mSounds.size();
        }



    }
}
