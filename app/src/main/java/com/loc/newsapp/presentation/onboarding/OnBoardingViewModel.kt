package com.loc.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HiltViewModel注解表明这个ViewModel类是由Hilt进行依赖注入的。
 * 通过@Inject构造函数注入AppEntryUseCases。
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    /**
     * 处理OnBoardingEvent事件。
     *
     * @param event OnBoardingEvent类型事件，根据不同的事件执行不同的操作。
     */
    suspend fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                appEntryUseCases.saveAppEntry()
            }
        }
    }

    /**
     * 在viewModelScope中异步保存应用入口标记。
     * 使用appEntryUseCases保存应用入口标记，封装了数据库操作。
     */
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }
    }
}
