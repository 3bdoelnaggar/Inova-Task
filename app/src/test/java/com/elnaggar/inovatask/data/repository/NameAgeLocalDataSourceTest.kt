package com.elnaggar.inovatask.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnaggar.inovatask.data.entity.NameAge
import com.elnaggar.inovatask.data.local.NameAgeLocalDataSource
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
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class NameAgeLocalDataSourceTest {
    @Inject
    lateinit var localDataSource: NameAgeLocalDataSource

    @get:Rule(order = 0)
    val hiltRule: HiltAndroidRule by lazy { HiltAndroidRule(this) }

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testScope = TestCoroutineScope()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testAddTheReadSuccess() {
        testScope.runBlockingTest {
            localDataSource.addNameAgeList(listOf(NameAge("Test Name", 92)))
            val list = localDataSource.getNameAgeList()
            assertEquals(list[0].name, "Test Name")
        }
    }

    @After
    fun cleanup() {
        testScope.cleanupTestCoroutines()
    }

}