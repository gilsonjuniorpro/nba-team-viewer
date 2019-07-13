package com.nba.ca.pojo

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class CacheTeams : RealmModel {
    var json_team: String = ""
}