package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
    title = "保持更新",
    description = "獲取來自世界各地的最新新聞資訊，讓您隨時瞭解全球大事。",
    image = R.drawable.onboarding1
),
Page(
    title = "隨時掌握",
    description = "第一時間掌握國際動態，不錯過任何重要新聞。",
    image = R.drawable.onboarding2
),
Page(
    title = "關注熱點",
    description = "探索全球熱點事件，持續關注最新進展，了解其背後的故事。",
    image = R.drawable.onboarding3
),


)
