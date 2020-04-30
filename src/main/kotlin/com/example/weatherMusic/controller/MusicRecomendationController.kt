package com.example.weatherMusic.controller

import com.example.weatherMusic.domain.model.Song
import com.example.weatherMusic.domain.service.impl.ServiceImpl
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(origins = ["*"])
class MusicRecomendationController(val serviceImpl: ServiceImpl) {

    @RequestMapping(method = [RequestMethod.GET],value = ["/"])
    fun findPlaylistByCityName(@RequestParam(required = true,value = "city") city : String ) : List<Song>{

        return try {
            val songsList = serviceImpl.recommendSongsCity(city)
            songsList
        } catch (e:Exception) {
            ArrayList()
        }
    }

    @RequestMapping(method = [RequestMethod.GET],value = ["/coords"])
    fun findPlaylistByCityCoords(@RequestParam(required = true,value = "lat") lat : String,@RequestParam(required = true,value = "lon") lon : String ) : List<Song>{

        return try {
            val songsList = serviceImpl.recommendSongs(lat,lon)
            songsList
        } catch (e:Exception) {
            ArrayList<Song>()
        }
    }
}
