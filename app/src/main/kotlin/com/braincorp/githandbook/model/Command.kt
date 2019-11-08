package com.braincorp.githandbook.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Command(
        var name: String,
        var parameter: String?,
        var description: String,
        var example: String
) : Parcelable