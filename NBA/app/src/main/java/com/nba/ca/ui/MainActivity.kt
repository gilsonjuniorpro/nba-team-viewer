package com.nba.ca.ui

import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nba.ca.R
import com.nba.ca.adapter.TeamAdapter
import com.nba.ca.controller.TeamController
import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.pojo.Team
import com.nba.ca.util.Utils
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    private var list: MutableList<Team>? = null
    private var sort: String = "ASC"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        Realm.init(this)

        downloadData(sort)

        var mLayoutManager = LinearLayoutManager(this)

        var mDividerItemDecoration = DividerItemDecoration(
            listTeams.context,
            mLayoutManager.orientation
        )

        listTeams.layoutManager = mLayoutManager
        listTeams.addItemDecoration(mDividerItemDecoration)

        ivSortDesc.setOnClickListener{ downloadData("DESC") }
        ivSortAsc.setOnClickListener{ downloadData("ASC") }
        ivSortWins.setOnClickListener{ downloadData("WINS") }
        ivSortLosses.setOnClickListener{ downloadData("LOSSES") }
    }

    private fun downloadData(sort:String) {
        doAsync {
            try{
                var response = if(Utils.hasConnection(baseContext)){
                    TeamController.listTeams() as ResponseTeams
                }else{
                    TeamController.listTeamsFromCache() as ResponseTeams
                }

                if(response.teams != null) {
                    list = when (sort) {
                        "ASC" -> response.teams!!.sortedBy { team -> team.full_name } as MutableList<Team>
                        "DESC" -> response.teams!!.sortedByDescending { team -> team.full_name } as MutableList<Team>
                        "WINS" -> response.teams!!.sortedBy { team -> team.wins } as MutableList<Team>
                        else -> response.teams!!.sortedBy { team -> team.losses } as MutableList<Team>
                    }
                }
            }catch (e: Exception){
                progress.visibility = View.GONE
                Log.e("TAG", "error trying to get API information: $e")
            }


            uiThread {
                progress.visibility = View.GONE
                updateUI()
            }
        }
    }

    private fun updateUI() {
        if(Utils.hasConnection(baseContext)){
            tvStatus.text = " "
        }else{
            tvStatus.text = "OFFLINE"
        }

        if(list != null && list!!.size > 0)
            listTeams.adapter = TeamAdapter(list!!, this)
    }



}
