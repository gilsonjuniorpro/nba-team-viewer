package com.nba.ca

import android.content.ClipData
import android.view.View
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nba.ca.controller.TeamController
import com.nba.ca.pojo.ResponseTeams
import com.nba.ca.ui.MainActivity
import com.nba.ca.util.Dominios
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    val rule : ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.nba.ca", appContext.packageName)
    }

    @Test
    fun verifyIfListTeamsLoads(){
        var response = TeamController.listTeams() as ResponseTeams
        assertEquals(response.teams!!.size, Dominios.AMOUNT_OF_TEAMS)
    }

    @Test
    fun verifyIfListPlayerLoads(){
        var response = TeamController.listTeams() as ResponseTeams
        assertEquals(response.teams!![0].players!!.size, Dominios.AMOUNT_OF_PLAYERS)
    }

    @Test
    fun simulateClickOnSortAsc(){
        onView(withId(R.id.ivSortAsc)).perform(click())
    }

    @Test
    fun simulateClickOnSortDesc(){
        onView(withId(R.id.ivSortDesc)).perform(click())
    }

    @Test
    fun simulateClickOnSortWins(){
        onView(withId(R.id.ivSortWins)).perform(click())
    }

    @Test
    fun simulateClickOnSortLosses(){
        onView(withId(R.id.ivSortLosses)).perform(click())
    }

    @Test
    fun simulateClickOnItem(){
        onView(withId(R.id.listTeams))
            .perform(swipeLeft())
            .check(matches(hasDescendant(withText("Atlanta Hawks"))))
    }


}
