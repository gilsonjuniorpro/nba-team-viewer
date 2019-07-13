package com.nba.ca.controller

import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.pojo.Team
import com.nba.ca.service.LogoService
import com.nba.ca.service.TeamService

object TeamController {

    fun listTeams(): ResponseTeams? {
        var responseTeam = TeamService.listTeams()
        var listTeam = responseTeam!!.teams!!.sortedBy { teams -> teams.full_name }

        var responseLogo = LogoService.listLogos()
        var listLogos = responseLogo!!.logos!!.sortedBy { logos -> logos.team_name }



        var teamsList = ArrayList<Team>()
        listTeam.forEachIndexed { i, value ->
            if (listLogos[i].team_name == value.full_name) {
                var team = listTeam[i]
                var logo = listLogos[i]
                team.logo = logo
                teamsList.add(team)
            }
        }

        var responseTeams: ResponseTeams? = ResponseTeams(teamsList)

        return responseTeams
    }

}