package com.loc.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * 本地用户管理器接口，用于处理应用内用户相关的操作
 */
interface LocalUserManger {
    /**
     * 保存应用入口数据
     *
     * 此方法用于在本地存储中记录应用已被访问的信息，可能涉及数据库或SharedPreferences等存储方式
     */
    suspend fun saveAppEntry()

    /**
     * 读取应用入口数据
     *
     * @return 返回一个Flow流，包含有关应用入口数据是否存在的布尔值
     *
     * 该方法用于从本地存储中检查应用入口数据是否存在，返回一个Flow对象以便于在协程中进行异步观察
     */
    fun readAppEntry(): Flow<Boolean>
}
