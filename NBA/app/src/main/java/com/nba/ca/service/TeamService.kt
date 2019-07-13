package com.nba.ca.service

import com.google.gson.Gson
import com.nba.ca.dao.CacheDAO
import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.pojo.Team
import com.nba.ca.util.Dominios
import java.net.URL

object TeamService {

    fun listTeams(): ResponseTeams? {
        val json = URL(Dominios.URL_JSON_NBA).readText()
        CacheDAO.saveTeamToCache(json)
        val teams: List<Team> = Gson().fromJson(json, Array<Team>::class.java).toList()
        return ResponseTeams(teams)
    }

}