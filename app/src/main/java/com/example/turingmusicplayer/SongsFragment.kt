package com.example.turingmusicplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turingmusicplayer.databinding.FragmentSongsBinding

class SongsFragment : Fragment() {
    private var _binding: FragmentSongsBinding? = null
    private val binding get() = _binding!!

    lateinit var recyclerView : RecyclerView
    lateinit var musicAdapter : MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        // import static constant 'com.aman.myaudioplayer.MainActivity.musicFiles'
        if (MusicUtils.musicFiles.size >= 1){
            musicAdapter = MusicAdapter(requireContext(), MusicUtils.musicFiles)
            recyclerView.adapter = musicAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}