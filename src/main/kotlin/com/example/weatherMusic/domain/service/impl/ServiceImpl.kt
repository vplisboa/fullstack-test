package com.example.weatherMusic.domain.service.impl

import com.example.weatherMusic.domain.model.Song
import com.example.weatherMusic.domain.service.ServiceRecoommendation
import com.example.weatherMusic.infrastructure.SongFactory
import com.example.weatherMusic.infrastructure.WeatherAPI
import com.wrapper.spotify.SpotifyApi
import org.springframework.stereotype.Service

@Service
class ServiceImpl(private val weatherAPI: WeatherAPI) : ServiceRecoommendation {

    var clientID = ""

    var clientSecret = ""

    var weatherAPIKey = ""

    override fun recommendSongs(lat: String, lon: String) : List<Song>{
        val response = weatherAPI.recoverByCoords(lat,lon,weatherAPIKey,"metric")

        return getSongsList(response)
    }

    override fun recommendSongsCity(city: String) : List<Song> {
        val response = weatherAPI.recoverByCity(city,weatherAPIKey,"metric")

        return getSongsList(response)
    }

    private fun findSongGenre(temperature: Double): String {
        return when {
            temperature >= 30 -> "party"
            15 <= temperature && temperature < 30 -> "pop"
            10 <= temperature && temperature < 15 -> "rock"
            else -> "classical"
        }
    }

    fun findRandomPlaylist(int: Int) : Int{
        return (0 until int).shuffled().first()
    }

    fun getSongsList(response: Map<String,Any>) : List<Song>{
        val weatherInformation = response["main"] as LinkedHashMap<*, *>
        val temperature = weatherInformation["temp"] as Double

        val spotifyApi = SpotifyApi.Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                .build()

        val clientCredentialsRequest = spotifyApi.clientCredentials()
                .build()

        val clientCredentials = clientCredentialsRequest.execute()

        spotifyApi.accessToken = clientCredentials.accessToken
        val songGenre = findSongGenre(temperature);
        val playlistList = spotifyApi.getCategoriesPlaylists(songGenre).build().execute().items
        val trackList = spotifyApi.getPlaylist(playlistList[findRandomPlaylist(playlistList.size)].id).build().execute().tracks.items
        val songsList = SongFactory.generateSongList(trackList)

        return songsList
    }
}