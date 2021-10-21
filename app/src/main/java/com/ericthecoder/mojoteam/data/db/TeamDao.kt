package com.ericthecoder.mojoteam.data.db

import androidx.room.*
import com.ericthecoder.mojoteam.data.db.DbConstants.Companion.TEAM_MEMBERS_TABLE_NAME
import com.ericthecoder.mojoteam.entities.TeamMember

@Dao
interface TeamDao {

    @Query("SELECT * FROM $TEAM_MEMBERS_TABLE_NAME")
    suspend fun getTeamMembers(): List<OfflineTeamMember>

    @Insert
    suspend fun insert(teamMember: OfflineTeamMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(teamMembers: List<OfflineTeamMember>)

    @Query("DELETE FROM $TEAM_MEMBERS_TABLE_NAME WHERE name = :name AND position = :position")
    suspend fun delete(name: String, position: String)

    @Query("DELETE FROM $TEAM_MEMBERS_TABLE_NAME")
    suspend fun deleteAll()
}
