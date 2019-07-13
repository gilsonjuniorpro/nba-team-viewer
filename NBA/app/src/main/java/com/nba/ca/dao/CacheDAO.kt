package com.nba.ca.dao

import com.nba.ca.pojo.CacheLogos
import com.nba.ca.pojo.CacheTeams
import io.realm.Realm

object CacheDAO{

    fun saveTeamToCache(json: String){
        var realm  = Realm.getDefaultInstance()
        var cache = CacheTeams()
        cache.json_team = json

        realm.beginTransaction()
        realm.deleteAll()
        realm.copyToRealm(cache)
        realm.commitTransaction()
    }

    fun saveLogoToCache(json: String){
        var realm  = Realm.getDefaultInstance()
        var cache = CacheLogos()
        cache.json_logo = json

        realm.beginTransaction()
        realm.copyToRealm(cache)
        realm.commitTransaction()
    }

    fun getTeamFromCache() : CacheTeams{
        var realm  = Realm.getDefaultInstance()
        return realm.where(CacheTeams::class.java).findFirst()!!
    }

    fun getLogoFromCache() : CacheLogos{
        var realm  = Realm.getDefaultInstance()
        return realm.where(CacheLogos::class.java).findFirst()!!
    }

}