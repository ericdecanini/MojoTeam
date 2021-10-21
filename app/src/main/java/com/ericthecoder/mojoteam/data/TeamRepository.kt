package com.ericthecoder.mojoteam.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TeamRepository {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val teamApi = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://ptitchevreuil.github.io/")
        .build()
        .create(TeamApi::class.java)

    suspend fun getTeamMembers() = teamApi.getTeamMembers()
}
