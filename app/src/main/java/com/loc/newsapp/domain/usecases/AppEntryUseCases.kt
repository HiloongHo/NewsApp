package com.loc.newsapp.domain.usecases

/**
 * AppEntryUseCases 数据类封装了应用程序入口的使用案例。
 * 它提供了一组操作应用程序入口数据的手段，包括读取和保存操作。
 *
 * @param readAppEntry 一个用于读取应用程序入口数据的函数。
 * @param saveAppEntry 一个用于保存应用程序入口数据的函数。
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)
