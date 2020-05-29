package demo.test2.mvp.model

import android.app.Application
import com.common.di.scope.ActivityScope
import com.common.integration.IRepositoryManager
import com.common.mvp.BaseModel
import com.google.gson.Gson
import com.yangyan.xxp.yangyannew.mvp.model.service.CommonService
import com.yangyan.xxp.yangyannew.mvp.model.service.cache.CommonCacheService
import demo.test2.mvp.contract.DemoContract
import demo.test2.mvp.model.entity.ImagesInfo
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.Reply
import io.rx_cache2.Source
import timber.log.Timber
import javax.inject.Inject


@ActivityScope
class DemoModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), DemoContract.Model {
    @Inject
    lateinit var mGson: Gson
    @Inject
    lateinit var mApplication: Application

    override fun getHomeData(pageIndex: Int): Observable<List<ImagesInfo>> {
        return mRepositoryManager.obtainCacheService(CommonCacheService::class.java)
                .getImagesByKey(mRepositoryManager.obtainRetrofitService(CommonService::class.java)
                        .getImagesByKey("rand", pageIndex),
                        DynamicKey(pageIndex))
                .map { reply: Reply<List<ImagesInfo>> ->
                    reply.data.apply {
                        when (reply.source) {
                            Source.CLOUD -> {
                                Timber.i("网络")
                            }
                            Source.MEMORY -> {
                                Timber.i("内存")
                            }
                            Source.PERSISTENCE -> {
                                Timber.i("硬碟")
                            }
                            else -> {
                            }
                        }
                    }
                }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
