package com.newsroom.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.newsroom.app.R
import com.newsroom.app.models.Article
import com.newsroom.app.repository.AndroidTestRepository
import com.newsroom.app.util.ServiceLocator
import kotlinx.android.synthetic.main.activity_news_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class BreakingNewsFragmentTest {


    private lateinit var testRepository : AndroidTestRepository

    @Before
    fun setup() {
        testRepository = AndroidTestRepository()
        ServiceLocator.newsRepository = testRepository
    }

    @After
    fun tearDown() {
        ServiceLocator.resetRepository()
    }

    @Test
    fun launchBreakingNewsFragment_IsVisible() {
        val scenario = launchFragmentInContainer<BreakingNewsFragment>(Bundle(), R.style.AppTheme)
        onView(withId(R.id.breakingNewsRecycler)).check(matches(isDisplayed()))
    }

    @Test
    fun checkNumberOfItems_returns1() {

        val scenario = launchFragmentInContainer<BreakingNewsFragment>(Bundle(), R.style.AppTheme)

        val navController = Mockito.mock(NavController::class.java)

        scenario.onFragment{
            Navigation.setViewNavController(it.view!!, navController)
        }

        var article : Article? =  null
        runBlocking {
            article = testRepository.getBreakingNews("",0).body()?.articles?.get(0)
        }

        val bundle = Bundle()
        bundle.putSerializable("article_arg", article)

        onView(withId(R.id.breakingNewsRecycler)).perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
            hasDescendant(withText("Title1")),click()
        ))

        //verify(navController).navigate(
            //R.id.action_breakingNewsFragment_to_articleFragment,bundle)

        onView(withId(R.id.titleArticleTV)).check(matches(isDisplayed()))

    }

}