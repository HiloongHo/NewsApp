package com.loc.newsapp.data.remote.dto

import com.loc.newsapp.domain.model.Article

/**
 * 新闻响应数据类，用于封装从新闻API获取的数据
 *
 * @property articles 列表形式的新闻文章集合
 * @property status 请求的状态，表示数据获取是否成功
 * @property totalResults 总的新闻文章数量
 */
data class NewsResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
