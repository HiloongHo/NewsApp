package com.loc.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.loc.newsapp.domain.model.Article

/**
 * 新闻分页数据源，用于从远程API获取新闻数据并提供给Paging库
 */
class NewsPagingSource(
    private val newsApi: NewsApi,  // 新闻API服务
    private val sources: String     // 新闻来源
): PagingSource<Int, Article>() {  // 分页数据源的泛型参数分别表示页码类型和数据项类型
    private var totalNewsCount = 0  // 已加载的新闻总数
    /**
     * 加载分页数据
     * @param params 分页加载参数，包含页码等信息
     * @return LoadResult类型，包含加载的数据和下一页的键
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1  // 获取当前页码，如果为null则默认为第1页
        return try {
            val newsResponce = newsApi.getNews(sources, page)  // 从API获取新闻数据
            totalNewsCount += newsResponce.articles.size  // 更新已加载的新闻计数
            val articles = newsResponce.articles.distinctBy { it.title }  // 去除标题重复的新闻文章
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponce.totalResults) null else page + 1,  // 确定是否有下一页
                prevKey = null  // 不支持逆向分页，因此prevKey始终为null
            )
        } catch (e: Exception) {
            e.printStackTrace()  // 打印异常堆栈信息
            LoadResult.Error(throwable = e)  // 返回加载错误结果，包含异常信息
        }
    }
    /**
     * 获取刷新分页的键
     * @param state PagingState，包含当前分页状态和数据
     * @return 刷新时使用的页码键，如果无法刷新则返回null
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)  // 找到锚点位置附近的页
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)  // 尝试获取刷新键
        }
    }
}
