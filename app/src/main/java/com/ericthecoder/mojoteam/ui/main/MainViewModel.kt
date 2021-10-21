package com.ericthecoder.mojoteam.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericthecoder.mojoteam.data.network.TeamRepository
import com.ericthecoder.mojoteam.entities.TeamMember
import kotlinx.coroutines.launch

// TODO: Decouple context from view model
class MainViewModel(context: Context) : ViewModel() {

    private val teamRepository = TeamRepository(context)

    private val teamMembersEmitter = MutableLiveData<List<TeamMember>>()
    val teamMembers: LiveData<List<TeamMember>> = teamMembersEmitter

    init {
        getTeamMembers()
    }

    private fun getTeamMembers() = viewModelScope.launch {
        val teamMembers = teamRepository.getTeamMembers()
        teamMembersEmitter.value = teamMembers
    }

    fun getTeamMembersOffline() = viewModelScope.launch {
        val teamMembers = teamRepository.getTeamMembersOffline()
        teamMembersEmitter.value = teamMembers
    }

    fun deleteTeamMember(teamMember: TeamMember) = viewModelScope.launch {
        val newTeamMembers = teamRepository.deleteTeamMember(teamMember)
        teamMembersEmitter.value = newTeamMembers
    }
}
