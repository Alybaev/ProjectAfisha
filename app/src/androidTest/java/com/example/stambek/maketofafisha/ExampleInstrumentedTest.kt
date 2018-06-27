package com.example.stambek.maketofafisha

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.stambek.maketofafisha.R.id.*
import com.example.stambek.maketofafisha.ui.cinema.MainActivity
import com.example.stambek.maketofafisha.ui.movie.SecondActivity
import kotlinx.android.synthetic.main.activity_shedule_.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


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

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Intents.init()
    }


    @Test
    fun checkWorkOfAfisha() {
        val mainActivityName = MainActivity::class.java!!.name
        IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(mainActivityName))
        intended(hasComponent(MainActivity::class.java!!.name))
        val recyclerView = mActivityRule.activity.list_view
        val itemCount = recyclerView.adapter.itemCount

        Log.d("Item count is ", itemCount.toString())
        //  choose random number for testing item in Recycle view


        // Scroll to end of page with position in Recycle view 1
        val secondActivityName = SecondActivity::class.java!!.name
        IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(secondActivityName))
        intended(hasComponent(SecondActivity::class.java!!.name))
        scrollAndClickRecycle(itemCount)
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
fun scrollAndClickRecycle(itemCount: Int) {
    onView(withId(list_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))

    // Perform click on definite view in Recycle view 1

    onView(withId(list_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemCount - 1, click()))



}
//Wait Class
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

class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {

    private val startTime: Long
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(
            resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}

