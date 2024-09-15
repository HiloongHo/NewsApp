package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.data.manager.LocalUserManagerImpl
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.repository.NewsRepositoryImpl
import com.loc.newsapp.domain.manager.LocalUserManger
import com.loc.newsapp.domain.repository.NewsRepository
import com.loc.newsapp.domain.usecases.news.GetNews
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import com.loc.newsapp.domain.usecases.qpp_entry.AppEntryUseCases
import com.loc.newsapp.domain.usecases.qpp_entry.ReadAppEntry
import com.loc.newsapp.domain.usecases.qpp_entry.SaveAppEntry
import com.loc.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        repository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(repository)
        )
    }
}
