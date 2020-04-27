package com.example.weatherMusic.infrastructure

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("weatherAPI",url = "api.openweathermap.org/data/2.5/")
interface WeatherAPI {
    //8ff8f1870902247f5bdee9d38be4b4ba
    @RequestMapping(method =[RequestMethod.GET],value = ["/weather"])
    fun recoverByCity(@RequestParam("q") city: String, @RequestParam("appid") key : String,@RequestParam("units") units:String): Map<String, Any>

    @RequestMapping(method =[RequestMethod.GET],value = ["/weather"])
    fun recoverByCoords(@RequestParam("lat") lat: String, @RequestParam("lon") long:String,@RequestParam("appid") key : String, @RequestParam("units") units:String): Map<String, Any>
}