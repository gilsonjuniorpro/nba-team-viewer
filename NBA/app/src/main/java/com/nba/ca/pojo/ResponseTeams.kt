package com.nba.ca.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseTeams (
    var teams : List<Team>?
): Parcelable