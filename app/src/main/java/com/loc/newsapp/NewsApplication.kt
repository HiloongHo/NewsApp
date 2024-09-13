package com.loc.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * NewsApplication 类是新闻应用的入口应用类，配合 Hilt 进行依赖注入初始化。
 * 继承自 Application 类，标记有 @HiltAndroidApp 注解，表示该应用使用 Hilt 框架进行依赖注入。
 */
@HiltAndroidApp
class NewsApplication : Application()
