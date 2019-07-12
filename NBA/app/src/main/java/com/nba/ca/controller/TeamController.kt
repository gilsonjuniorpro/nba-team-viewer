package com.nba.ca.controller

import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.service.TeamService

object TeamController {

    fun listTeams(): ResponseTeams? {
        return TeamService.listTeams()
    }

}