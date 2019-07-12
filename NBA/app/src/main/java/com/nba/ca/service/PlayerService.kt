package com.nba.ca.service

import com.google.gson.Gson
import com.nba.ca.pojo.ResponsePlayers
import com.nba.ca.util.Dominios
import java.net.URL

object PlayerService {

    fun listPlayers(): ResponsePlayers? {
        val json = URL(Dominios.URL_JSON_NBA).readText()
        return Gson().fromJson(json, ResponsePlayers::class.java)
    }

}