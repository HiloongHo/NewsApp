package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimens.MediumPadding1

/**
 * 显示文章列表的可组合函数。
 *
 * @param modifier 用于修改Layout的参数。
 * @param articles 包含文章的分页项目。
 * @param onClick 点击文章时的回调函数。
 */
@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
){
    // 处理分页结果并决定是否显示文章列表
    val handlePagingResult = handlePagingResult(articles)
    if (handlePagingResult) {
        // 使用LazyColumn布局来显示文章列表
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let {
                    // 为每篇文章创建卡片并设置点击事件
                    ArticleCard(article = it, onClick = {onClick(it)})
                }
            }
        }
    }

}

/**
 * 处理分页结果的可组合函数。
 *
 * @param articles 包含文章的分页项目。
 * @return Boolean值，决定是否显示文章列表。
 */
@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>
):Boolean{
    // 获取分页加载状态
    val loadState = articles.loadState
    // 检查是否有错误加载状态
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        // 如果正在加载中，显示Shimmer效果
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        // 如果有错误，显示空屏幕
        error != null -> {
            EmptyScreen()
            false
        }
        // 其他情况，返回true以显示文章列表
        else -> true
    }
}

/**
 * 创建Shimmer效果的可组合函数，用于在加载中显示占位符。
 */
@Composable
private fun ShimmerEffect(){
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumPadding1)
    ) {
        // 重复创建10个文章卡片的Shimmer效果
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}
