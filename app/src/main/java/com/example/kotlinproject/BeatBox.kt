package com.example.kotlinproject

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioManager
import android.media.SoundPool
import com.example.kotlinproject.BeatBox.Companion.SAMPLE_SOUND
import com.example.kotlinproject.model.Sound

class BeatBox constructor(context: Context?){
    var mSound = ArrayList<Sound>()
    var mContext : Context ?= null
    var mAssetManager : AssetManager ?= null
    var mSoundPool : SoundPool ?= null

    companion object{
        const val MAX_STREEM = 3
        const val SAMPLE_SOUND = "sample_sounds"
    }

    init {
        if (context != null){
            mContext = context.applicationContext
            mSoundPool = SoundPool(MAX_STREEM,
                       AudioManager.STREAM_MUSIC,
                        0)
            loadSound()

        }
    }

    fun loadSound(){
        mAssetManager = mContext!!.assets
        var soundName = mAssetManager!!.list(SAMPLE_SOUND)
        for (i in 0 until soundName!!.size) {
            var path : String = SAMPLE_SOUND + "/" + soundName[i]
            var sound = Sound(path)
            mSound.add(sound)
            load(path, sound)

        }
    }
    fun load(path : String , sound: Sound){
        var afd : AssetFileDescriptor = mAssetManager!!.openFd(path)
        var soundId : Int = mSoundPool!!.load(afd, 1)
        sound.soundId = soundId
    }

    fun play (sound : Sound){
        /*if (sound.soundId == null)
            return*/
        mSoundPool!!.play(sound.soundId!!,1.0f, 1.0f,
                                0,0,1.0f)
    }

    fun release(){
        mSoundPool!!.release()
    }
}