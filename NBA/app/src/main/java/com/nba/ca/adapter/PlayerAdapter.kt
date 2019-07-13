package com.nba.ca.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nba.ca.R
import com.nba.ca.pojo.Player
import kotlinx.android.synthetic.main.item_player_list.view.*

class PlayerAdapter(items : List<Player>,mContext: Context) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>(){

    private var list:List<Player> = items
    private var context = mContext

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvFisrtName.text = list[position].first_name
        holder.tvLastName.text = list[position].last_name
        holder.tvPosition.text = list[position].position
        holder.tvNumber.text = list[position].number.toString()

        holder.layBase.setOnClickListener { openDetailPlayer(list[position]) }
    }

    private fun openDetailPlayer(player: Player) {
        /*var it = Intent(context, DetailTeamActivity::class.java)
        it.putExtra("team", team)
        context.startActivity(it)*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_player_list,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvFisrtName = view.tvFisrtName!!
        val tvLastName = view.tvLastName!!
        val tvPosition = view.tvPosition!!
        val tvNumber = view.tvNumber!!
        val layBase = view.layBase!!
    }


}