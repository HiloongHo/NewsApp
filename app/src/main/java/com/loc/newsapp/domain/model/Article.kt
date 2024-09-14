package com.loc.newsapp.domain.model

/**
 * Article数据类，表示一篇文章的所有必要信息
 *
 * @property author 文章作者的姓名
 * @property content 文章的正文内容
 * @property description 文章的简短描述
 * @property publishedAt 文章的发布日期和时间
 * @property source 文章的来源，包含来源的名称和ID
 * @property title 文章的标题
 * @property url 文章的原始URL链接
 * @property urlToImage 文章的配图URL链接
 */
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
