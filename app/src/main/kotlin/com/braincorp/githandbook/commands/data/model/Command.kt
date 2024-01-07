package com.braincorp.githandbook.commands.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Command(
    @PrimaryKey var id: Int,
    var name: String,
    var parameter: String?,
    var description: String,
    var example: String
) : Parcelable
