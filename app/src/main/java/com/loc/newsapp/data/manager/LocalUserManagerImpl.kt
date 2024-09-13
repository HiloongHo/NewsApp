package com.loc.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.loc.newsapp.domain.manager.LocalUserManger
import com.loc.newsapp.util.Constants
import com.loc.newsapp.util.Constants.APP_ENTRY
import com.loc.newsapp.util.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 实现 LocalUserManger 接口的类，用于管理本地用户的数据操作。
 *
 * @param context Android 上下文，用于访问数据存储。
 */
class LocalUserManagerImpl(
    private val context: Context
): LocalUserManger {

    /**
     * 保存应用启动状态，标记应用是否已被首次启动。
     *
     * 通过编辑数据存储，将 APP_ENTRY 键对应的值设置为 true，表示应用已被首次启动。
     */
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    /**
     * 读取应用启动状态。
     *
     * 返回一个 Flow 对象，该对象映射数据存储中 APP_ENTRY 键对应的值，如果键不存在则默认为 false。
     *
     * @return Flow<Boolean> 应用启动状态的流，true 表示应用已被首次启动，false 表示未被首次启动或键不存在。
     */
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

// 定义一个懒加载的 DataStore 实例，使用 Preferences 作为类型参数，存储在 USER_SETTINGS 命名的空间中。
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

// 用于定义所有偏好设置的键的常量类。
private object PreferencesKeys {
    // 定义一个布尔类型的键，使用 Constants 中定义的 APP_ENTRY 名称。
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}
