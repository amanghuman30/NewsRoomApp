package com.newsroom.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.newsroom.app.R
import com.newsroom.app.repository.AndroidTestRepository
import com.newsroom.app.util.ServiceLocator
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class BreakingNewsFragmentTest {

    @Test
    fun launchBreakingNewsFragment_IsVisible() {
        ServiceLocator.newsRepository = AndroidTestRepository()

        val scenario = launchFragmentInContainer<BreakingNewsFragment>(Bundle(), R.style.AppTheme)

        onView(withId(R.id.breakingNewsRecycler)).check(matches(isDisplayed()))

        ServiceLocator.resetRepository()
    }
}