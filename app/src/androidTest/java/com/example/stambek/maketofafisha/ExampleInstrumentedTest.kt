package com.example.stambek.maketofafisha

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.stambek.maketofafisha.R.id.list_view
import com.example.stambek.maketofafisha.R.id.list_view2
import com.example.stambek.maketofafisha.ui.cinema.MainActivity
import com.example.stambek.maketofafisha.ui.movie.SecondActivity
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_shedule_.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class CinemaAppTest {
    @get:Rule
    val mActivityRule = ActivityTestRule(
            MainActivity::class.java)

    @Test
    fun checkWorkOfAfisha() {
        Thread.sleep(1000)
        var rnd = Random()
        var itemsCinema = mActivityRule.activity.list_view.adapter.itemCount
        var casualNum = rnd.nextInt(itemsCinema)


        onView(withId(list_view)).perform(scrollToPosition<RecyclerView.ViewHolder>(casualNum))
        Thread.sleep(3000)
        onView(withId(list_view)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(casualNum, click()))
        Thread.sleep(3000)

        onView(withId(list_view2)).check(matches(isDisplayed()))
        onView(withId(list_view2)).perform(scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(list_view2)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(3000)
        onView(withId((R.id.imageView))).check(matches(isDisplayed()))
        onView(withId((R.id.information))).check(matches(isDisplayed()))

        onView(isRoot()).perform(ViewActions.pressBack())
        onView(isRoot()).perform(ViewActions.pressBack())
        Thread.sleep(3000)
        onView(withId(list_view)).check(matches(isDisplayed()))

    }
}

