package com.ericthecoder.mojoteam.ui.newmember

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericthecoder.mojoteam.data.network.TeamRepository
import com.ericthecoder.mojoteam.entities.TeamMember
import kotlinx.coroutines.launch

class NewMemberViewModel(context: Context) : ViewModel() {

    private val teamRepository = TeamRepository(context)

    fun insertTeamMember(teamMember: TeamMember) = viewModelScope.launch {
        teamRepository.insertTeamMember(teamMember)
    }
}
