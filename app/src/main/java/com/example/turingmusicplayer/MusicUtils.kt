package com.example.turingmusicplayer

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object MusicUtils {
    val musicFiles: ArrayList<MusicFiles> = ArrayList()

    @JvmStatic
    fun getAllAudio(context: Context): ArrayList<MusicFiles> {
        val tempAudioList = ArrayList<MusicFiles>()
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media.DATA, // path
            MediaStore.Audio.Media.TITLE, // title
            MediaStore.Audio.Media.ARTIST, // artist
            MediaStore.Audio.Media.DURATION // duration
        )
        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val path = cursor.getString(0)
                val title = cursor.getString(1)
                val artist = cursor.getString(2)
                val duration = cursor.getString(3)
                val musicFiles = MusicFiles(path, title, artist, duration)
                tempAudioList.add(musicFiles)
            }
            cursor.close()
        }
        return tempAudioList
    }
}