package com.nba.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nba.ca.R
import com.nba.ca.adapter.TeamAdapter
import com.nba.ca.controller.TeamController
import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.pojo.Team
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.StrictMode
import androidx.recyclerview.widget.DividerItemDecoration


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

    fun downloadData(sort:String) {
        doAsync {
            var response = TeamController.listTeams() as ResponseTeams

            if(response.teams != null) {
                list = when (sort) {
                    "ASC" -> response.teams!!.sortedBy { team -> team.full_name } as MutableList<Team>
                    "DESC" -> response.teams!!.sortedByDescending { team -> team.full_name } as MutableList<Team>
                    "WINS" -> response.teams!!.sortedBy { team -> team.wins } as MutableList<Team>
                    else -> response.teams!!.sortedBy { team -> team.losses } as MutableList<Team>
                }
            }

            uiThread {
                progress.visibility = View.GONE
                updateUI()
            }
        }
    }

    private fun updateUI() {
        if(list != null && list!!.size > 0)
            listTeams.adapter = TeamAdapter(list!!, this)
    }
}
