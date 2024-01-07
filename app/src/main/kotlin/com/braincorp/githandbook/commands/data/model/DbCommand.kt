package com.braincorp.githandbook.commands.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class DbCommand(
    @PrimaryKey val id: Int,
    val name: String,
    val parameter: String?,
    val description: String,
    val example: String
) : Parcelable
