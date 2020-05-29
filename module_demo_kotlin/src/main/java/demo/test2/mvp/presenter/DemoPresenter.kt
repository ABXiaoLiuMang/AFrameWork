package demo.test2.mvp.presenter

import android.app.Application
import com.common.di.scope.ActivityScope
import com.common.integration.AppManager
import com.common.mvp.BasePresenter
import com.common.utils.RxLifecycleUtils
import demo.test2.mvp.contract.DemoContract
import demo.test2.mvp.model.entity.ImagesInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import me.jessyan.rxerrorhandler.core.RxErrorHandler
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber
import timber.log.Timber
import javax.inject.Inject

@ActivityScope
class DemoPresenter
@Inject
constructor(model: DemoContract.Model, rootView: DemoContract.View) :
        BasePresenter<DemoContract.Model, DemoContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mAppManager: AppManager

    fun getHomeData() {

        mModel.getHomeData(1)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    //                    if (pullToRefresh)
//                        mRootView.showLoading()//显示下拉刷新的进度条
//                    else
//                        mRootView.startLoadMore()//显示上拉加载更多的进度条
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    //                    if (pullToRefresh)
//                        mRootView.hideLoading()//隐藏下拉刷新的进度条
//                    else {
//                        mRootView.endLoadMore()//隐藏上拉加载更多的进度条
//                    }
                }
                .doOnError {
                    //                    mAdapter.loadMoreEnd()
                }
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(object : ErrorHandleSubscriber<List<ImagesInfo>>(mErrorHandler) {
                    override fun onNext(t: List<ImagesInfo>) {
                        //success

                        Timber.d(t.toString())

                    }

                    override fun onError(t: Throwable) {

                        Timber.d(t.toString())

                    }

                })
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
