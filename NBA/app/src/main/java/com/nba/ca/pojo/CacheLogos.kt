package com.nba.ca.pojo

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class CacheLogos : RealmModel {
    var json_logo: String = ""
}