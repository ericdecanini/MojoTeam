package com.ericthecoder.mojoteam.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.ericthecoder.mojoteam.R
import com.ericthecoder.mojoteam.entities.TeamMember
import com.ericthecoder.mojoteam.ui.newmember.NewMemberActivity
import com.ericthecoder.mojoteam.ui.newmember.NewMemberViewModel
import com.ericthecoder.mojoteam.ui.view.TeamMemberView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_team_member.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = MainViewModel(this)
        observeTeamMembers()

        fab.setOnClickListener { startActivity(Intent(this, NewMemberActivity::class.java)) }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTeamMembersOffline()
    }

    // TODO: Implement a more performant way of resetting the team list
    private fun observeTeamMembers() = viewModel.teamMembers.observe(this) { teamMembers ->
        resetTeamList()
        drawTeamList(teamMembers)
    }

    private fun drawTeamList(teamMembers: List<TeamMember>) {
        teamMembers.forEach { teamMember ->
            val teamMemberView = TeamMemberView(this).apply { setData(teamMember) }
            teamMemberView.delete_button.setOnClickListener {
                viewModel.deleteTeamMember(teamMember)
            }
            team_list.addView(teamMemberView)
        }
    }

    private fun resetTeamList() {
        team_list.removeAllViews()
    }
}
