package com.loc.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen
import com.loc.newsapp.presentation.onboarding.OnBoardingViewModel
import com.loc.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 主活动类，应用程序的入口点。
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * 初始化主活动。
     * 设置启动屏幕和活动的内容。
     *
     * @param savedInstanceState 如果活动之前被杀死，但在重新启动之前其状态已保存，则为包含保存的状态的Bundle；否则为null。
     */
    @Inject
    lateinit var useCases: AppEntryUseCases
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 确保窗口的装饰（如状态栏和导航栏）不会自动调整
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // 安装启动屏幕以提供平滑的启动体验
        installSplashScreen()
        lifecycleScope.launch {
            useCases.readAppEntry().collect{
                Log.d("test", it.toString())
            }
        }
        // 设置Composable内容
        setContent {
            // 使用自定义的主题包装器
            NewsAppTheme {
                // 在Box中放置欢迎屏幕，作为主活动的初始界面
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}
