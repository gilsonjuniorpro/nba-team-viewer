package com.nba.ca.service

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.nba.ca.pojo.Response
import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.pojo.Team
import com.nba.ca.util.Dominios
import com.nba.ca.util.JSONParser
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser
import java.net.URL

object TeamService {

    fun listTeams(): ResponseTeams? {
        val json = URL(Dominios.URL_JSON_NBA).readText()
        //val json = "[{\"wins\": 45,\"losses\": 20,\"full_name\": \"Boston Celtics\",\"id\": 1,\"players\": [{\"id\": 37729,\"first_name\": \"Kadeem\",\"last_name\": \"Allen\",\"position\": \"SG\",\"number\": 45}]},{\"wins\": 20,\"losses\": 44,\"full_name\": \"Brooklyn Nets\",\"id\": 2,\"players\": [{\"id\": 802,\"first_name\": \"Quincy\",\"last_name\": \"Acy\",\"position\": \"F\",\"number\": 13}]}]"

        val homedateList: List<Team> = Gson().fromJson(json, Array<Team>::class.java).toList()


        return Gson().fromJson(json, ResponseTeams::class.java)
    }

}