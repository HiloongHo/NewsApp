package com.loc.newsapp.data.remote

import com.loc.newsapp.data.remote.dto.NewsResponce
import com.loc.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 新闻API接口，用于从远程服务器获取新闻数据
 */
interface NewsApi {
    /**
     * 获取新闻信息
     *
     * 该函数通过Retrofit框架从远程服务器获取新闻数据，使用了 suspend 修饰符，使其可以在协程中调用
     * @param string 指定新闻来源的字符串
     * @param page 页码，用于分页加载数据
     * @param apiKey API密钥，用于验证请求的合法性，默认为预定义的API_KEY
     * @return 返回一个NewsResponce对象，包含新闻数据和元数据
     */
    @GET("everything")
    suspend fun getNews(
        @Query("sources") string: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en", // 默认返回英文新闻
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponce
}
