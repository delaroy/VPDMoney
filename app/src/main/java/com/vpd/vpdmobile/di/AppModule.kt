package com.vpd.vpdmobile.di

import com.vpd.vpdmobile.data.db.TransactionDao
import com.vpd.vpdmobile.repository.authentication.AuthRepository
import com.vpd.vpdmobile.repository.authentication.BaseAuthRepository
import com.vpd.vpdmobile.repository.authentication.BaseAuthenticator
import com.vpd.vpdmobile.repository.authentication.FirebaseAuthenticator
import com.vpd.vpdmobile.repository.transaction.TransactionHistoryRepository
import com.vpd.vpdmobile.repository.transaction.TransactionHistoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthenticator() : BaseAuthenticator {
        return  FirebaseAuthenticator()
    }

    @Singleton
    @Provides
    fun provideRepository(
        authenticator : BaseAuthenticator
    ) : BaseAuthRepository {
        return AuthRepository(authenticator)
    }

    @Singleton
    @Provides
    fun provideTransactionRepository(
        dao: TransactionDao
    ) : TransactionHistoryRepository {
        return TransactionHistoryRepositoryImpl(dao)
    }
}