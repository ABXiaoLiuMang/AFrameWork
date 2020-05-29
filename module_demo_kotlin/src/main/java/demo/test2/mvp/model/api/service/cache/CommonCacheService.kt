package com.yangyan.xxp.yangyannew.mvp.model.service.cache

import demo.test2.mvp.model.entity.ImagesInfo
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.LifeCache
import io.rx_cache2.Reply
import java.util.concurrent.TimeUnit

/**
 * Description : 緩存--RxCache
 */
interface CommonCacheService {
    companion object {
        /**
         * 缓存有效時間  30分鐘
         */
        const val CACHE_LONG = 30L
    }

    /**
     * 根据key获取图片
     */
    @LifeCache(duration = CACHE_LONG, timeUnit = TimeUnit.MINUTES)
    fun getImagesByKey(newAtlasList: Observable<List<ImagesInfo>>, key: DynamicKey): Observable<Reply<List<ImagesInfo>>>


}