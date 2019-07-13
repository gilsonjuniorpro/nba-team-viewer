package com.nba.ca.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
    var id : Int?,
    var full_name : String?,
    var wins : Int?,
    var losses : Int?,
    var players : List<Player>?,
    var logo : Logo?
): Parcelable