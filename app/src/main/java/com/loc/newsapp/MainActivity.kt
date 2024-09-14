package com.loc.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.activity.viewModels
import com.loc.newsapp.presentation.nvgraph.NavGraph
import com.loc.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

// 使用Dagger Hilt注解来支持依赖注入
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // 使用 Hilt 和 Android ViewModel 来获取 MainViewModel 实例
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 确保窗口的装饰（如状态栏和导航栏）不会自动调整
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 安装启动屏幕并设置启动屏幕可见条件
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        // 设置Composable内容
        setContent {
            // 使用自定义的主题包装器
            NewsAppTheme {
                // 在Box中放置欢迎屏幕，作为主活动的初始界面
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
