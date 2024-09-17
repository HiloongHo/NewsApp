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

    /**
     * 提供NewsApi的实例。
     *
     * @return NewsApi接口的实例，通过Retrofit构建。
     */
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }

    /**
     * 提供新闻仓库的实现。
     *
     * @param newsApi NewsApi接口的实例。
     * @return NewsRepository接口的实现类NewsRepositoryImpl。
     */
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    /**
     * 提供新闻相关的UseCases。
     *
     * @param repository 新闻仓库实例。
     * @return NewsUseCases对象，包含获取新闻的UseCase。
     */
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
