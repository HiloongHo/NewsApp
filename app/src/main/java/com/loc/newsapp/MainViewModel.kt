package com.loc.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.qpp_entry.AppEntryUseCases
import com.loc.newsapp.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlinx.coroutines.launch

// 使用Hilt进行依赖注入的ViewModel类
@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases // 用于处理应用入口逻辑的UseCases
) : ViewModel() {

    // 控制启动屏显示状态的变量，初始设为true
    var splashCondition by mutableStateOf(true)
        private set // 只允许MainViewModel内部修改此属性

    // 控制应用启动时导航目的地的变量，初始设为应用启动导航路线
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set // 只允许MainViewModel内部修改此属性

    // 初始化时启动协程，根据应用入口逻辑更新导航目的地，并在延迟后隐藏启动屏
    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().onEach { showStartFromHomeScreen ->
                // 根据应用的入口逻辑更新 startDestination
                startDestination = if (showStartFromHomeScreen) {
                    Route.NewsNavigation.route
                } else {
                    Route.AppStartNavigation.route
                }

                // 模拟延迟以显示启动页 3 秒
                delay(3000)
                splashCondition = false
            }.launchIn(this) // 保证 flow 在协程中运行
        }
    }
}
