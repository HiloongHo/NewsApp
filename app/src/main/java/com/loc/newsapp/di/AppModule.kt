package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.domain.manager.LocalUserManger
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import com.loc.newsapp.domain.usecases.ReadAppEntry
import com.loc.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger的模块类，用于提供应用级别的依赖项。
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * 提供本地用户管理器的实现。
     *
     * @param application 应用程序上下文。
     * @return LocalUserManger接口的实现类LocalUserManagerImpl。
     */
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManger = LocalUserManagerImpl(application)

    /**
     * 提供与应用入口相关的UseCases。
     *
     * @param localUserManger 本地用户管理器，用于用户相关操作。
     * @return AppEntryUseCases对象，包含读取和保存应用入口的UseCases。
     */
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}
