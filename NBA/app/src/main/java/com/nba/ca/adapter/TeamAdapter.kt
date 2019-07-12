package com.nba.ca.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nba.ca.ui.DetailTeamActivity
import com.nba.ca.R
import com.nba.ca.pojo.Team
import kotlinx.android.synthetic.main.item_team_list.view.*

class TeamAdapter(items : List<Team>,mContext: Context) : RecyclerView.Adapter<TeamAdapter.ViewHolder>(){

    private var list:List<Team> = items
    private var context = mContext

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFullName.text = list[position].full_name
        holder.tvWin.text = list[position].wins.toString()
        holder.tvLosses.text = list[position].losses.toString()
        holder.ivFlag.setOnClickListener { openDetailTeam(list[position]) }
    }

    private fun openDetailTeam(team: Team) {
        var it = Intent(context, DetailTeamActivity::class.java)
        it.putExtra("team", team)
        context.startActivity(it)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_team_list,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ivFlag = view.ivFlag!!
        val tvFullName = view.tvFullName!!
        val tvWin = view.tvWin!!
        val tvLosses = view.tvLosses!!
    }


}