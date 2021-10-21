package com.ericthecoder.mojoteam.data.network

import com.ericthecoder.mojoteam.entities.TeamMember
import retrofit2.http.GET

interface TeamApi {

    @GET("mojo/team.json")
    suspend fun getTeamMembers(): List<TeamMember>
}
