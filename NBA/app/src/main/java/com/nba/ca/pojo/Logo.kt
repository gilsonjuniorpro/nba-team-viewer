package com.nba.ca.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Logo (
    var team_name : String?,
    var team_logo : String?
): Parcelable