package com.example.weatherMusic.infrastructure

import com.example.weatherMusic.domain.model.Song
import com.wrapper.spotify.model_objects.specification.PlaylistTrack

object SongFactory {

    fun generateSongList(list: Array<PlaylistTrack>) : List<Song> {
        val songsList = ArrayList<Song>()
        for (song in list) {
            val songInfo = Song(song.track.name,song.track.artists.map { it.name }.toString())
            songsList.add(songInfo)
        }
        return songsList
    }
}