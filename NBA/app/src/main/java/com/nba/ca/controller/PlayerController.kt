package com.nba.ca.controller

import com.nba.ca.pojo.ResponsePlayers
import com.nba.ca.service.PlayerService

object PlayerController {

    fun listPlayers(): ResponsePlayers? {
        return PlayerService.listPlayers()
    }

}