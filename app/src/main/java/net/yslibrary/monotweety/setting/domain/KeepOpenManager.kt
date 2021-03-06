package net.yslibrary.monotweety.setting.domain

import net.yslibrary.monotweety.base.di.AppScope
import net.yslibrary.monotweety.data.setting.SettingDataManager
import rx.Observable
import javax.inject.Inject

/**
 * Created by yshrsmz on 2016/10/03.
 */
@AppScope
class KeepOpenManager @Inject constructor(private val settingDataManager: SettingDataManager) {

  fun get(): Observable<Boolean> = settingDataManager.keepOpen()

  fun set(enabled: Boolean) = settingDataManager.keepOpen(enabled)
}