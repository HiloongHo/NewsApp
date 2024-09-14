package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import com.loc.newsapp.data.remote.NewsPagingSource

/**
 * 新闻仓库的实现类，负责数据的获取和处理
 */
class NewsRepositoryImpl(
    private val newsApi: NewsApi // 注入NewsApi服务
) : NewsRepository {

    /**
     * 获取新闻数据流
     *
     * @param sources 新闻来源的列表
     * @return 返回一个Flow流，包含分页数据类型的Article列表
     */
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        // 配置分页，每次加载10条数据
        val pagingConfig = PagingConfig(
            pageSize = 10,
        )

        // 创建一个Pager，使用NewsPagingSource作为数据源
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                NewsPagingSource(newsApi, sources.joinToString(separator = ","))
            }
        ).flow
    }
}
