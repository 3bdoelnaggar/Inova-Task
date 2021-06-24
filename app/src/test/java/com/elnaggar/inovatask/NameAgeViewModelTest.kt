package com.elnaggar.inovatask

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.elnaggar.inovatask.data.repository.NameAgeRepository
import com.elnaggar.inovatask.ui.nameAgeList.NameAgeListState
import com.elnaggar.inovatask.ui.nameAgeList.NameAgeListViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.test.assertNotNull


@ExperimentalCoroutinesApi
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NameAgeViewModelTest {

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
    fun getNameAgeListReturnSuccessState() {
        val viewModel = NameAgeListViewModel(nameAgeRepository)
        val nameAgeObserver: Observer<NameAgeListState> = Observer {
        }

        try {
            viewModel.stateLiveData.observeForever(nameAgeObserver)
            viewModel.getNameAgeList()
            val expected = viewModel.stateLiveData.getOrAwaitValue() as? NameAgeListState.Error
            assertNotNull(expected)
        } finally {
            viewModel.stateLiveData.removeObserver(nameAgeObserver)
        }
    }


    @After
    fun cleanup() {
        testScope.cleanupTestCoroutines()
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 50,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

