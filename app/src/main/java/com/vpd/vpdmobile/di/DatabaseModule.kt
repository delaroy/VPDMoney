package com.vpd.vpdmobile.di

import android.content.Context
import com.vpd.vpdmobile.data.db.VpdMobileDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @[Provides Singleton]
    fun provideDatabase(
        @ApplicationContext context: Context
    ): VpdMobileDatabase =
        VpdMobileDatabase.build(context = context)

}