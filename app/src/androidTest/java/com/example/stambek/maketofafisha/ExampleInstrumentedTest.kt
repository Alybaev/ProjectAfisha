package com.example.stambek.maketofafisha

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.stambek.maketofafisha.ui.cinema.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.util.Log
import com.example.stambek.maketofafisha.R.id.imageView
import com.example.stambek.maketofafisha.R.id.information
import com.example.stambek.maketofafisha.ui.movie.SecondActivity
import kotlinx.android.synthetic.main.activity_shedule_.*
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
//        onView(withId(R.id.list_view)).perform(RecyclerViewActions.scrollToPosition< RecyclerView.ViewHolder>(3))
        val recyclerView = mActivityRule.activity.list_view
        val itemCount = recyclerView.adapter.itemCount

        Log.d("Item count is ", itemCount.toString())
        //  choose random number for tsting item in Recycle view
//        var rnd = Random()
//        var rndNum = rnd.nextInt(itemCount)
        //Check is Main activity launched
        Intents.init()
        val mainActivityName = MainActivity::class.java!!.name
        IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(mainActivityName))
        intended(hasComponent(MainActivity::class.java!!.name))
        // Scroll to end of page with position in Recycle view 1
        onView(withId(R.id.list_view))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
        // Perform click on definite view in Recycle view 1
        onView(withId(R.id.list_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemCount - 1, click()))
        val secondActivityName = SecondActivity::class.java!!.getName()
        IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(secondActivityName))
        intended(hasComponent(SecondActivity::class.java!!.name))
        // Scroll to end of page with position in Recycle view 2
//        rndNum = rnd.nextInt(itemCount - 1)
        onView(withId(R.id.list_view2))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
        // Perform click on definite view in Recycle view 2
        onView(withId(R.id.list_view2))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemCount - 1, click()))
        // is Displayed Text and Image of movie
        onView(withId(imageView)).check(matches(isDisplayed()))
        onView(withId(information)).check(matches(isDisplayed()))
        //Back Button
        Espresso.pressBack()
        Espresso.pressBack()

    }
}

private class WaitActivityIsResumedIdlingResource(private val activityToWaitClassName: String) : IdlingResource {

    private val instance: ActivityLifecycleMonitor
    @Volatile private var resourceCallback: IdlingResource.ResourceCallback? = null
    internal var resumed = false

    private val isActivityLaunched: Boolean
        get() {
            val activitiesInStage = instance.getActivitiesInStage(Stage.RESUMED)
            for (activity in activitiesInStage) {
                if (activity.javaClass.name == activityToWaitClassName) {
                    return true
                }
            }
            return false
        }

    init {
        instance = ActivityLifecycleMonitorRegistry.getInstance()
    }

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        resumed = isActivityLaunched
        if (resumed && resourceCallback != null) {
            resourceCallback!!.onTransitionToIdle()
        }

        return resumed
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}
