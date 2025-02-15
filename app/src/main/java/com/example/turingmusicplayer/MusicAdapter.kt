package com.example.turingmusicplayer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turingmusicplayer.databinding.MusicItemsBinding

class MusicAdapter(
    private val mContext: Context,
    private val mFiles: ArrayList<MusicFiles>
) : RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: MusicItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = MusicItemsBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val musicFile = mFiles[position]
        holder.binding.apply {
            musicFileName.text = musicFile.title
            val image = getAlbumArt(musicFile.path)
            if (image != null) {
                Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(musicImg)
            } else {
                musicImg.setImageResource(R.drawable.music)
            }
        }
    }

    override fun getItemCount(): Int {
        return mFiles.size
    }

    private fun getAlbumArt(uri: String): ByteArray? {
        val retriever = android.media.MediaMetadataRetriever()
        retriever.setDataSource(uri)
        val art = retriever.embeddedPicture
        retriever.release()
        return art
    }
}