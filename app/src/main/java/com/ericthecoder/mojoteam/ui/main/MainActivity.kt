package com.ericthecoder.mojoteam.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ericthecoder.mojoteam.R
import com.ericthecoder.mojoteam.entities.TeamMember
import com.ericthecoder.mojoteam.ui.view.TeamMemberView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel(this)
        observeTeamMembers()
    }

    // TODO: Implement a more performant way of resetting the team list
    private fun observeTeamMembers() = viewModel.teamMembers.observe(this) { teamMembers ->
        resetTeamList()
        drawTeamList(teamMembers)
    }

    private fun drawTeamList(teamMembers: List<TeamMember>) {
        teamMembers.forEach { teamMember ->
            val teamMemberView = TeamMemberView(this).apply { setData(teamMember) }
            team_list.addView(teamMemberView)
        }
    }

    private fun resetTeamList() {
        team_list.removeAllViews()
    }
}
