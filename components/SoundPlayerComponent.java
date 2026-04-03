package com.example.engine.components;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class SoundPlayerComponent extends Component {

    /* ================================
    VARIABLES
    ================================ */

    private SoundPool soundPool;
    private int[] sounds;
    private int[] resources;


    /* ================================
    CONSTRUCTEURS
    ================================ */

    //
    public SoundPlayerComponent(int[] resources) {
        this.resources = resources;
    }


    /* ================================
    METHODES
    ================================ */

    //
    @Override
    public void onAttach() {
        assert parent != null;
        Context context = parent.root.context;

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build())
                .build();

        sounds = new int[resources.length];

        for(int i = 0; i < resources.length; i++) {
            sounds[i] = soundPool.load(context, resources[i], 1);
        }
    }

    //
    public void playById(int soundId, int loop) {
        if(soundPool == null || sounds == null) return;

        soundPool.play(sounds[soundId], 1f, 1f, 1, loop, 1f);
    }
}