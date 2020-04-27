package com.example.weatherMusic.controller

import com.example.weatherMusic.domain.model.Song
import com.example.weatherMusic.domain.service.impl.ServiceImpl
import com.wrapper.spotify.SpotifyApi
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
class MusicRecomendationController(val serviceImpl: ServiceImpl) {

    @RequestMapping(method = [RequestMethod.GET],value = ["/"])
    fun test(@RequestParam(required = true,value = "city") city : String ) : List<Song>{

        try {
            val songsList = serviceImpl.recommendSongsCity(city)
            return songsList
        } catch (e:Exception) {
            return ArrayList<Song>()
        }
    }
}
