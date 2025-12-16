package com.example.birthdaywishesapp.ui.util

import android.content.Context
import android.media.MediaPlayer
import com.example.birthdaywishesapp.R
import com.example.birthdaywishesapp.viewmodel.MusicType

class MusicPlayer(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    fun play(type: MusicType) {
        stop()

        val musicRes = when (type) {
            MusicType.BACKGROUND -> R.raw.bg_flow_music
            MusicType.CELEBRATION -> R.raw.birthday_celebration
            MusicType.NONE -> return
        }

        mediaPlayer = MediaPlayer.create(context, musicRes).apply {
            isLooping = type == MusicType.BACKGROUND
            start()
        }
    }

    fun stop() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
