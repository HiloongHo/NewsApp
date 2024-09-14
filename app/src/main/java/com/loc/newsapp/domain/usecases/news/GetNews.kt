package com.loc.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * 获取新闻的Use Case，负责从新闻仓库中获取新闻数据
 *
 * @property newsRepository 新闻数据仓库，提供获取新闻数据的方法
 */
class GetNews(
    private val newsRepository: NewsRepository
) {
    /**
     * 调用函数，用于从仓库中获取新闻数据
     *
     * @param sources 新闻来源的列表，用于指定获取新闻的数据源
     * @return 返回一个Flow流，该流包含PagingData分页数据，用于展示新闻文章列表
     */
    operator fun invoke(sources: List<String>) : Flow<PagingData<Article>>{
        return newsRepository.getNews(sources)
    }
}
