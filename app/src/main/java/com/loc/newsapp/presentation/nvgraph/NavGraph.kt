package com.loc.newsapp.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel

/**
 * 定义应用的导航图，包含应用的各个导航目的地及其导航逻辑
 *
 * @param startDestination 应用启动时的初始目的地路由字符串
 */
@Composable
fun NavGraph(
    startDestination: String
) {
    // 创建一个导航控制器实例
    val navController: NavHostController = rememberNavController()

    // 定义整个应用的导航主机，负责处理全局导航逻辑
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // 定义应用启动时的导航图，包含引导屏幕
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            // 定义引导屏幕的具体内容
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                // 获取引导屏幕的ViewModel实例
                val viewModel: OnBoardingViewModel = hiltViewModel()
                // 显示引导屏幕，并将事件处理函数传递给ViewModel
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        // 定义新闻相关导航图，包含新闻导航器屏幕
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            // 定义新闻导航器屏幕的具体内容
            composable(route = Route.NewsNavigatorScreen.route) {
                // 此处为示例，应替换为实际的新闻导航器屏幕实现
                Text(text = "News Navigator Screen")
            }
        }
    }
}
