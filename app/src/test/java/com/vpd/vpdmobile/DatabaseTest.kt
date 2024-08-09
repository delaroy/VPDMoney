package com.vpd.vpdmobile

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.vpd.vpdmobile.data.db.VpdMobileDatabase
import org.junit.After
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
internal abstract class DatabaseTest {

    protected val database by lazy {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VpdMobileDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        database.close()
    }
}