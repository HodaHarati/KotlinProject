package com.example.kotlinproject.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlinproject.R
import kotlinx.android.synthetic.main.activity_single_fragment.*

abstract class SinglefragmentActivity : AppCompatActivity() {
     abstract fun creatFragment() : Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_single_fragment)



       val fragmentManager = supportFragmentManager
        val fragment = fragmentManager
            .findFragmentById(R.id.fragment_container)

        if(fragment == null){
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container,creatFragment()).commit()
        }
    }
}
