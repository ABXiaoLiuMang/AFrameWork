package commonsdk.mmkv.utils

import android.text.TextUtils
import com.blankj.utilcode.util.GsonUtils
import com.felix.base.model.AccountModel
import timber.log.Timber

object AccountUtils {

    /**外部调用缓存方法**/
    fun cacheAccount(any: AccountModel?) {
        if (null == any) return
        // 缓存数据到本地
        cacheAccountWithMMKV(any)
    }

    /**缓存pojo到本地**/
    private fun cacheAccountWithMMKV(any: AccountModel?) {
        var cacheJson = ""
        if (null != any) {
            cacheJson = GsonUtils.toJson(any)
        }
        Timber.e("---------mmkv cacheJson------s--------" + cacheJson)
        MMKVUtils.put(MMKVConstants.ACCOUNT_CACHE, cacheJson)
    }

    /**获取缓存到本地的pojo信息**/
    fun getCacheAccount(): AccountModel? {
        val cacheJson: String? = MMKVUtils.getString(MMKVConstants.ACCOUNT_CACHE)
        if (TextUtils.isEmpty(cacheJson)) return null
        Timber.e("---------mmkv cacheJson------g--------" + cacheJson)
        return GsonUtils.fromJson(cacheJson, AccountModel::class.java)
    }

}