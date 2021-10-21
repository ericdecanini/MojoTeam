package com.ericthecoder.mojoteam.data.network

import android.content.Context
import android.util.Log
import com.ericthecoder.mojoteam.data.db.OfflineTeamMember
import com.ericthecoder.mojoteam.data.db.TeamDatabase
import com.ericthecoder.mojoteam.entities.TeamMember
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.UnknownHostException

class TeamRepository(context: Context) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val teamApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://ptitchevreuil.github.io/")
        .build()
        .create(TeamApi::class.java)

    private val teamDao = TeamDatabase.getDatabase(context).teamDao()

    suspend fun getTeamMembers() = try {
        teamApi.getTeamMembers().also {
            teamDao.insertAll(convertToOfflineTeamMembers(it))
        }
    } catch (e: UnknownHostException) {
        convertFromOfflineTeamMembers(teamDao.getTeamMembers())
    }

    suspend fun getTeamMembersOffline() = convertFromOfflineTeamMembers(teamDao.getTeamMembers())

    suspend fun insertTeamMember(teamMember: TeamMember) = teamDao.insert(
        convertSingleTeamMember(teamMember)
    )

    private fun convertSingleTeamMember(teamMember: TeamMember) = teamMember.let {
        OfflineTeamMember(
            it.name,
            it.position,
            it.platform,
            it.pic
        )
    }

    private fun convertFromOfflineTeamMembers(offlineTeamMembers: List<OfflineTeamMember>) =
        offlineTeamMembers.map {
            TeamMember(
                it.name,
                it.position,
                it.platform,
                it.pic
            )
        }

    private fun convertToOfflineTeamMembers(teamMembers: List<TeamMember>) =
        teamMembers.map {
            OfflineTeamMember(
                it.name,
                it.position,
                it.platform,
                it.pic
            )
        }
}
