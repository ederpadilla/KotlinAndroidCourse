package eder.padilla.kotlinandroidcourse.mediaplayer.adapter

import eder.padilla.kotlinandroidcourse.mediaplayer.model.Song

/**
 * Created by ederpadilla on 17/06/17.
 */
interface OnSongSelected {
    fun onSongSelected(song : Song)
}