package com.vpd.vpdmobile.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vpd.vpdmobile.data.db.VpdMobileDatabase.Companion.DATABASE_VERSION
import com.vpd.vpdmobile.data.transaction.TransactionHistory

@Database(
    entities = [TransactionHistory::class], version = DATABASE_VERSION
)
abstract class VpdMobileDatabase : RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "vpd_database"

        @Volatile
        private var database: VpdMobileDatabase? = null

        fun build(
            context: Context
        ): VpdMobileDatabase {
            return database ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    VpdMobileDatabase::class.java,
                    DATABASE_NAME
                )
                databaseBuilder.fallbackToDestructiveMigration()
                val instance = databaseBuilder.build()
                instance.openHelper.writableDatabase
                database = instance
                instance
            }
        }
    }
}