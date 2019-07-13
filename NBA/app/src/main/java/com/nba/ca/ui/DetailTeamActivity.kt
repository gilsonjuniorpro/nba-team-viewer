package com.nba.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nba.ca.R
import com.nba.ca.adapter.PlayerAdapter
import com.nba.ca.pojo.Player
import com.nba.ca.pojo.Team
import com.nba.ca.util.Dominios
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity() {

    private var list: List<Player>? = null
    private var team: Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        team = intent.getParcelableExtra("team")

        tvTeamName.text = team!!.full_name
        tvWins.text = team!!.wins.toString()
        tvLosses.text = team!!.losses.toString()

        list = team!!.players

        var mLayoutManager = LinearLayoutManager(this)

        var mDividerItemDecoration = DividerItemDecoration(
            listPlayers.context,
            mLayoutManager.orientation
        )

        listPlayers.layoutManager = mLayoutManager
        listPlayers.addItemDecoration(mDividerItemDecoration)
        listPlayers.adapter = PlayerAdapter(list!!, this)

        var urlImage = Dominios.URL_JSON_LOGO_IMAGE + team!!.logo!!.team_logo

        Glide.with(this)
            .load(urlImage)
            .into(tvTeamFlag)
    }
}
