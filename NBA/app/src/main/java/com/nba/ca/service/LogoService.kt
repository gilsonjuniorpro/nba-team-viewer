package com.nba.ca.service

import com.google.gson.Gson
import com.nba.ca.dao.CacheDAO
import com.nba.ca.pojo.ResponseLogo
import com.nba.ca.util.Dominios
import java.net.URL

object LogoService {

    fun listLogos(): ResponseLogo? {
        val json = URL(Dominios.URL_JSON_LOGO).readText()
        CacheDAO.saveLogoToCache(json)
        return Gson().fromJson(json, ResponseLogo::class.java)
    }

}