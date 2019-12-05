package com.example.kotlinproject.controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.BeatBox
import com.example.kotlinproject.R
import com.example.kotlinproject.model.Sound
import kotlinx.android.synthetic.main.list_item_music.*
import kotlinx.android.synthetic.main.list_item_music.view.*
import kotlinx.android.synthetic.main.list_item_music.view.button_music

/**
 * A simple [Fragment] subclass.
 */
class BeatBoxFragment : Fragment() {
    var beatBox = BeatBox(context)
    var mRecyclerView: RecyclerView? = null
    var soundAdapter: SoundAdapter? = null

    companion object {
        fun newInstance(): BeatBoxFragment {
            val args = Bundle()
            val fragment = BeatBoxFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beatBox = BeatBox(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_beat_box, container, false)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView!!.layoutManager = GridLayoutManager(context, 3)
        soundAdapter = SoundAdapter(beatBox.mSound)
        mRecyclerView!!.adapter = soundAdapter

        return view
    }


    inner class SoundHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mSound: Sound? = null
        var mButton : Button = itemView.findViewById(R.id.button_music)
        init {

           mButton.setOnClickListener { view -> mSound?.let { beatBox.play(it) } }
        }

        fun bindSound(sound: Sound) {

            mSound = sound
            mButton?.text = mSound!!.name
        }

    }

    inner class SoundAdapter(soundlist: List<Sound>?) : RecyclerView.Adapter<SoundHolder>() {

        var mSound: ArrayList<Sound>? = soundlist as ArrayList<Sound>?

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.list_item_music, parent, false)
            return SoundHolder(view)
        }

        override fun getItemCount(): Int {
            return mSound!!.size
        }

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            holder.bindSound(mSound!!.get(position))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

}
