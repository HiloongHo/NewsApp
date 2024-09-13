package com.loc.newsapp.domain.usecases

import com.loc.newsapp.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

/**
 * 读取应用入口的用例
 *
 * 该类用于在需要时通过 [LocalUserManger] 类来读取应用的入口状态。
 * 它提供了一个 suspend 函数，返回一个 Flow 类型的布尔值流，表示应用入口的读取结果。
 *
 * @property localUserManger LocalUserManger 的实例，用于管理本地用户数据。
 */
class ReadAppEntry(
    private val localUserManger: LocalUserManger
){
    /**
     * 调用函数来读取应用入口
     *
     * 该函数作为操作符重载，通过调用 [localUserManger.readAppEntry] 来异步读取应用的入口状态。
     * 它返回一个 Flow 类型的布尔值，表示应用入口的读取结果。
     *
     * @return Flow<Boolean> 表示应用入口的读取结果的布尔值流。
     */
    suspend operator fun invoke() : Flow<Boolean>{
        return localUserManger.readAppEntry()
    }
}
