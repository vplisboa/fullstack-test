package com.example.weatherMusic.domain.service

import com.example.weatherMusic.domain.model.Song

interface ServiceRecoommendation {

    fun recommendSongs(lat: String, lon: String) : List<Song>

    fun recommendSongsCity(city: String) : List<Song>
}