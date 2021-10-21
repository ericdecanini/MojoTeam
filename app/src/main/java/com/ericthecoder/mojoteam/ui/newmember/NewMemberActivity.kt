package com.ericthecoder.mojoteam.ui.newmember

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ericthecoder.mojoteam.R
import com.ericthecoder.mojoteam.data.network.TeamRepository
import com.ericthecoder.mojoteam.entities.TeamMember
import com.ericthecoder.mojoteam.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_new_member.*

class NewMemberActivity : AppCompatActivity() {

    private val viewModel = NewMemberViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_member)

        submit_button.setOnClickListener { submit() }
    }

    private fun submit() {
        val teamMember = TeamMember(
            name.text.toString(),
            position.text.toString(),
            location.text.toString(),
            profile_pic.text.toString()
        )

        viewModel.insertTeamMember(teamMember)
        finish()
    }
}
