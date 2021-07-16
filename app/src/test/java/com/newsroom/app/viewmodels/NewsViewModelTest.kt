package com.newsroom.app.viewmodels

import android.app.Application
import android.content.Context
import android.service.autofill.Validators.not
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.newsroom.app.getOrAwaitValue
import com.newsroom.app.repository.TestRepository
import com.newsroom.app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var testRepository: TestRepository

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        val app = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        //val app = ApplicationProvider.getApplicationContext<Context>() as Application
        testRepository = TestRepository()
        newsViewModel = NewsViewModel(app, testRepository, testDispatcher)

        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getBreakingNews_ReturnsList() {
        runBlockingTest {
            newsViewModel.getBreakingNews("")

            val news = newsViewModel.breakingNewsLiveData.getOrAwaitValue()

            //assertThat(news.data?.articles?.size).isEqualTo(1)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

}