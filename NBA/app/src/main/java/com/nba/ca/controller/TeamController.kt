package com.nba.ca.controller

import com.google.gson.Gson
import com.nba.ca.dao.CacheDAO
import com.nba.ca.pojo.*
import com.nba.ca.service.LogoService
import com.nba.ca.service.TeamService

object TeamController {

    fun listTeams(): ResponseTeams? {
        //for better performance just uncomment the line below
        //return TeamService.listTeams()

        //and comment these lines, this remove the images
        /* */
        var responseTeam = TeamService.listTeams()
        var listTeam = responseTeam!!.teams!!.sortedBy { teams -> teams.full_name }

        var responseLogo = LogoService.listLogos()
        var listLogos = responseLogo!!.logos!!.sortedBy { logos -> logos.team_name }

        var teamsList = insertLogosInsideTeams(listTeam, listLogos)

        return ResponseTeams(teamsList)
        /* */
    }


    fun getTeamFromCache() : CacheTeams {
        return CacheDAO.getTeamFromCache()
    }


    fun getLogoFromCache() : CacheLogos {
        return CacheDAO.getLogoFromCache()
    }


    fun listTeamsFromCache(): ResponseTeams? {
        var rawTeams = getTeamFromCache()
        var rawLogos = getLogoFromCache()

        var listTeam: List<Team> = convertTeamStringToJson(rawTeams)
        var listLogos: List<Logo> = convertLogoStringToJson(rawLogos)

        var teamsList = insertLogosInsideTeams(listTeam, listLogos)

        return ResponseTeams(teamsList)
    }


    fun convertTeamStringToJson(rawTeams: CacheTeams): List<Team> {
        return Gson().fromJson(rawTeams.json_team, Array<Team>::class.java)
            .toList().sortedBy { teams -> teams.full_name }
    }


    fun convertLogoStringToJson(rawLogos: CacheLogos): List<Logo> {
        var responseLogo = Gson().fromJson(rawLogos.json_logo, ResponseLogo::class.java)

        return responseLogo.logos!!.sortedBy { logos -> logos.team_name }
    }


    fun insertLogosInsideTeams(
        listTeam: List<Team>,
        listLogos: List<Logo>
    ): ArrayList<Team>{
        var teamsList = ArrayList<Team>()
        listTeam.forEachIndexed { i, value ->
            if (listLogos[i].team_name == value.full_name) {
                var team = listTeam[i]
                var logo = listLogos[i]
                team.logo = logo
                teamsList.add(team)
            }
        }
        return teamsList
    }
}