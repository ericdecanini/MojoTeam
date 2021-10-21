package com.ericthecoder.mojoteam.ui.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ericthecoder.mojoteam.R
import com.ericthecoder.mojoteam.entities.TeamMember
import kotlinx.android.synthetic.main.list_item_team_member.view.*

class TeamMemberView(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    private var view = inflate(context, R.layout.list_item_team_member, null)

    init {
        addView(view)
    }

    fun setData(data: TeamMember) {
        bind(data)
    }

    // TODO: Look at grey and white profile pics
    private fun bind(teamMember: TeamMember) {
        view.name.text = teamMember.name
        view.position.text = teamMember.position
        teamMember.platform?.let { view.platform.text = it }

        teamMember.pic?.let {
            Glide.with(context)
                .load(it)
                .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.placeholder_gray)))
                .into(view.profile_pic)
        }
    }
}
