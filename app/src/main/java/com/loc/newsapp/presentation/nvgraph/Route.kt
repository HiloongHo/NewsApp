package com.loc.newsapp.presentation.nvgraph

/**
 * 定义应用中各个屏幕导航路线的密封类。
 * 该类用于集中管理应用内不同界面的导航路径，提高路径管理的便捷性和可维护性。
 */
sealed class Route(
    val route: String
) {
    // 定义导航至启动屏幕的路径
    object OnBoardingScreen : Route("onboarding_screen")
    // 定义导航至主屏幕的路径
    object HomeScreen : Route("home_screen")
    // 定义导航至搜索屏幕的路径
    object SearchScreen : Route("search_screen")
    // 定义导航至书签屏幕的路径
    object BookmarkScreen : Route("bookmark_screen")
    // 定义导航至详情屏幕的路径
    object DetailScreen : Route("detail_screen")
    // 定义应用启动导航的路径
    object AppStartNavigation : Route("app_start_navigation")
    // 定义新闻导航的路径
    object NewsNavigation : Route("news_navigation")
    // 定义新闻导航器屏幕的路径
    object NewsNavigatorScreen : Route("news_navigator_screen")
}
