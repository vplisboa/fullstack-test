package com.example.weatherMusic.domain.service.impl

import com.example.weatherMusic.domain.model.Song
import com.example.weatherMusic.domain.service.ServiceRecoommendation
import com.example.weatherMusic.infrastructure.*
import com.wrapper.spotify.SpotifyApi
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ServiceImpl(private val weatherAPI: WeatherAPI) : ServiceRecoommendation {

    @Value("\${spotify.clientId}")
    lateinit var clientId : String

    @Value("\${spotify.clientSecret}")
    lateinit var clientSecret : String

    @Value("\${weatherAPI.key}")
    lateinit var weatherAPIKey :String

    override fun recommendSongs(lat: String, lon: String) : List<Song>{
        val response = weatherAPI.recoverByCoords(lat,lon,weatherAPIKey,UNIT)

        return getSongsList(response)
    }

    override fun recommendSongsCity(city: String) : List<Song> {
        val response = weatherAPI.recoverByCity(city,weatherAPIKey,UNIT)

        return getSongsList(response)
    }

    private fun findSongGenre(temperature: Double): String {
        return when {
            temperature >= 30 -> PARTY
            15 <= temperature && temperature < 30 -> POP
            10 <= temperature && temperature < 15 -> ROCK
            else -> CLASSICAL
        }
    }

    fun findRandomPlaylist(int: Int) : Int{
        return (0 until int).shuffled().first()
    }

    fun getSongsList(response: Map<String,Any>) : List<Song>{
        val weatherInformation = response["main"] as LinkedHashMap<*, *>
        val temperature = weatherInformation["temp"] as Double

        val spotifyApi = SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build()

        val clientCredentialsRequest = spotifyApi.clientCredentials()
                .build()

        val clientCredentials = clientCredentialsRequest.execute()

        spotifyApi.accessToken = clientCredentials.accessToken
        val songGenre = findSongGenre(temperature);
        val playlistList = spotifyApi.getCategoriesPlaylists(songGenre).build().execute().items
        val trackList = spotifyApi.getPlaylist(playlistList[findRandomPlaylist(playlistList.size)].id).build().execute().tracks.items

        return SongFactory.generateSongList(trackList)
    }
}