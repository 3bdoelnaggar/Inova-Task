package com.elnaggar.inovatask.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NameAgeRepositoryTest {

    @Inject
    lateinit var nameAgeRepository: NameAgeRepository

    @get:Rule(order = 0)
    val hiltRule: HiltAndroidRule by lazy { HiltAndroidRule(this) }

    private val testScope = TestCoroutineScope()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testFetchThenCacheThenReadData() {
        testScope.runBlockingTest {
            val result = nameAgeRepository.getNameAgeList()
            assertEquals(result[0].name, "Abdalla")
        }
    }

    @Test
    fun testFetchThenCacheThenReadDataToFail() {
        testScope.runBlockingTest {
            val result = nameAgeRepository.getNameAgeList()
            assertEquals(result[5].name, "Abdalla")
        }
    }


    @After
    fun cleanup() {
        testScope.cleanupTestCoroutines()
    }
}