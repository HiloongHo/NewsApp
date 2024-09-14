package com.loc.newsapp.domain.usecases.qpp_entry

import com.loc.newsapp.domain.manager.LocalUserManger

/**
 * 用于保存应用条目到本地用户的用例类
 *
 * 该类封装了保存应用条目的逻辑，使用 LocalUserManger 来执行保存操作
 */
class SaveAppEntry(
    // LocalUserManger 的引用，用于执行与本地用户相关的操作
    private val localUserManger: LocalUserManger
) {
    /**
     * 调用该函数以保存应用条目
     *
     * 该函数是 suspend 函数，意味着它只能在协程上下文中调用或通过其他 suspend 函数调用
     */
    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}
