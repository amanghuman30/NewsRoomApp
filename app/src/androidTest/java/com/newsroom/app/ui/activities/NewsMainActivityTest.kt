package com.newsroom.app.ui.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.newsroom.app.R
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class NewsMainActivityTest {

    @Test
    fun launchActivity_CheckRecyclerVisibility() {

        val activityScenario = ActivityScenario.launch(NewsMainActivity::class.java)

        onView(withId(R.id.breakingNewsRecycler)).check(matches(isDisplayed()))

        activityScenario.close()
    }

}