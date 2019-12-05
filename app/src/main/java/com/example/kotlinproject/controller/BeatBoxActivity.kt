package com.example.kotlinproject.controller

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class BeatBoxActivity :SinglefragmentActivity(){
    override fun creatFragment(): Fragment {
        return BeatBoxFragment.newInstance()
    }

    companion object{
        fun newIntent(context: Context) :Intent{
            return  Intent(context,BeatBoxActivity::class.java)
        }
    }


}
