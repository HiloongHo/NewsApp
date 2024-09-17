package com.loc.newsapp.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.model.Source
import com.loc.newsapp.presentation.Dimens.ArticleCardSize
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding
import com.loc.newsapp.presentation.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.Dimens.SmallIconSize
import com.loc.newsapp.ui.theme.NewsAppTheme

// 文章卡片组件
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article, // 文章数据
    onClick: () -> Unit // 点击事件处理
) {
    val context = LocalContext.current // 获取当前上下文

    // Row 布局，用于横向排列图片和文本内容
    Row(modifier = modifier.clickable { onClick() }) { // 点击触发 onClick 事件
        // 异步加载图片
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize) // 设置图片大小
                .clip(MaterialTheme.shapes.medium), // 设置图片剪裁样式
            model = ImageRequest.Builder(context).data(article.urlToImage).build(), // 图片URL
            contentDescription = null, // 图片内容描述
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // 图片缩放模式
        )

        // Column 布局，用于纵向排列标题和来源等信息
        Column(
            verticalArrangement = Arrangement.SpaceBetween, // 子项之间保持间隔
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding) // 设置内边距
                .height(ArticleCardSize) // 设置高度
        ) {
            // 文章标题
            Text(
                text = article.title, // 标题内容
                style = MaterialTheme.typography.bodyMedium, // 文本样式
                color = colorResource(
                    id = R.color.text_title // 标题颜色
                ),
                maxLines = 2, // 最大行数
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis // 超出部分显示省略号
            )

            // Row 布局，横向排列来源名称、发布时间等信息
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically // 子项垂直居中
            ) {
                // 文章来源
                Text(
                    text = article.source.name, // 来源名称
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold), // 字体加粗
                    color = colorResource(
                        id = R.color.body // 来源文本颜色
                    ),
                )

                // 间隔
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                // 时间图标
                Icon(
                    painter = painterResource(id = R.drawable.ic_time), contentDescription = null, // 图标资源
                    modifier = Modifier.size(SmallIconSize), // 图标大小
                    tint = colorResource(id = R.color.body) // 图标颜色
                )

                // 间隔
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))

                // 发布时间
                Text(
                    maxLines = 1,
                    text = article.publishedAt, // 发布时间内容
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold), // 字体加粗
                    color = colorResource(
                        id = R.color.body // 文本颜色
                    ),
                )
            }
        }
    }
}

// 文章卡片预览
@Preview(showBackground = true) // 显示背景预览
@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES) // 夜间模式预览
@Composable
fun ArticleCardPreview() {
    NewsAppTheme {
        ArticleCard(
            article = Article(
                author = "Loc", // 作者
                content = "This is the content", // 内容
                description = "This is the description", // 描述
                publishedAt = "2023-05-01", // 发布时间
                source = Source(id = "abc-news", name = "ABC News"), // 来源
                title = "", // 标题
                url = "https://abcnews.com", // URL
                urlToImage = "https://abcnews.com/image.jpg" // 图片URL
            )
        ) {

        }
    }
}
