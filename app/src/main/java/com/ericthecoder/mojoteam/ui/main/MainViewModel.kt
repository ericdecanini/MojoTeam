package com.ericthecoder.mojoteam.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericthecoder.mojoteam.data.TeamRepository
import com.ericthecoder.mojoteam.entities.TeamMember
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val teamRepository = TeamRepository()

    private val teamMembersEmitter = MutableLiveData<List<TeamMember>>()
    val teamMembers: LiveData<List<TeamMember>> = teamMembersEmitter

    init {
        getTeamMembers()
    }

    private fun getTeamMembers() = viewModelScope.launch {
        val teamMembers = teamRepository.getTeamMembers()
        teamMembersEmitter.value = teamMembers
    }
}
