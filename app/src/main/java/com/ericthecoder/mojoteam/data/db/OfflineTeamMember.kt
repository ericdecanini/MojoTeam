package com.ericthecoder.mojoteam.data.db

import androidx.room.Entity

@Entity(
    tableName = DbConstants.TEAM_MEMBERS_TABLE_NAME,
    primaryKeys = ["name", "position"]
)
data class OfflineTeamMember(
    val name: String,
    val position: String,
    val platform: String? = "",
    val pic: String?,
)
