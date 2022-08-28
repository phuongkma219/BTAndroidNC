package com.example.bai1

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bai1.databinding.FragmentBt3Binding

class Bt3Fragment: Fragment() {
    private lateinit var binding: FragmentBt3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBt3Binding.inflate(layoutInflater)
      val songs=  MediaUtils.getListAudio(requireContext())
        if (songs.isEmpty()){
            binding.rootView.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
        }
        else{
            binding.rootView.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
            binding.item = songs[0]

        }
        val mediaPlayer = MediaPlayer()

        var currentSong =0
        binding.icActionNext.setOnClickListener {

            if (currentSong <songs.size-1){
                currentSong++
            }
            else {
                currentSong = 0
            }

//            mediaPlayer.stop()
//            mediaPlayer.setDataSource(requireContext(), Uri.parse(songs[currentSong].data))
//            mediaPlayer.prepare()
//            mediaPlayer.start()
        }
        binding.icActionPrevious.setOnClickListener {

            if (currentSong in 1 until songs.size-1){
                currentSong--
            }
            else currentSong =songs.size-1
//            mediaPlayer.stop()
//            mediaPlayer.setDataSource(requireContext(), Uri.parse(songs[currentSong].data))
//            mediaPlayer.prepare()
//            mediaPlayer.start()
        }
        binding.icActionPlay.setOnClickListener {

            mediaPlayer.setDataSource(requireContext(), Uri.parse(songs[currentSong].data))

            if (mediaPlayer.isPlaying){
                mediaPlayer.stop()
            }
           else{
                mediaPlayer.prepare()
                mediaPlayer.start()
            }
            Log.d("hhh", "onCreateView: ")
        }
        return binding.root
    }
}