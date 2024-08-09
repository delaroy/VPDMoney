package com.vpd.vpdmobile.di

import com.vpd.vpdmobile.data.db.TransactionDao
import com.vpd.vpdmobile.data.db.VpdMobileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DaoModule {

    @[Provides Singleton]
    fun providesTransactionDao(
        database: VpdMobileDatabase,
    ): TransactionDao = database.transactionDao

}