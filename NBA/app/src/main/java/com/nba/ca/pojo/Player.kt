package com.nba.ca.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player (
    var id: Int?,
    var first_name : String?,
    var last_name : String?,
    var position : String?,
    var number : Int?
): Parcelable