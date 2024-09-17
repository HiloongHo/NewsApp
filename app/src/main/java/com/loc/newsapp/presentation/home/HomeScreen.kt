package com.loc.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.newsapp.R
import com.loc.newsapp.presentation.Dimens.MediumPadding1
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.nvgraph.Route

/**
 * 主屏幕的组合式函数，用于显示新闻文章列表和搜索栏。
 *
 * @param articles 包含新闻文章的分页项目。
 * @param navigate 导航到其他屏幕的函数。
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigate: (String) -> Unit) {
    // 记住前10篇文章的标题，用作marquee文本。
    // 使用remember来保持titles的值，当articles列表变化时会重新计算
    val titles by remember {
        // 计算显示的文章标题字符串
        derivedStateOf {
            // 当文章数量超过10篇时，截取前10篇的标题并用特定分隔符连接
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9)) // 获取前10篇文章
                    .joinToString(separator = "\uD83d\uDFE5") { it.title } // 用图标分隔标题
            } else {
                // 当文章数量不多于10篇时，不显示任何标题
                ""
            }
        }
    }

    // 主屏幕的布局构建。
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        // 显示应用logo。
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )

        // 间距。
        Spacer(modifier = Modifier.height(MediumPadding1))

        // 搜索栏，点击时导航到搜索屏幕。
        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigate(Route.SearchScreen.route) },
            onSearch = {})

        // 间距。
        Spacer(modifier = Modifier.height(MediumPadding1))

        // 显示滚动文本的标题。
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder),
            maxLines = 1
        )

        // 间距。
        Spacer(modifier = Modifier.height(MediumPadding1))

        // 文章列表，点击文章导航到详情屏幕。
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigate(Route.DetailScreen.route)
            }
        )
    }
}
