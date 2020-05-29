package demo.test2.mvp.presenter

import android.app.Application

import com.common.integration.AppManager
import com.common.di.scope.ActivityScope
import com.common.mvp.BasePresenter;
import com.common.imageloader.ImageLoader;
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject;

import demo.test2.mvp.contract.TestMvpKTContract


@ActivityScope
class TestMvpKTPresenter
@Inject
constructor(model: TestMvpKTContract.Model, rootView: TestMvpKTContract.View) :
BasePresenter<TestMvpKTContract.Model, TestMvpKTContract.View>(model,rootView) {
    @Inject
    lateinit var mErrorHandler:RxErrorHandler
    @Inject
    lateinit var mApplication:Application
    @Inject
    lateinit var mImageLoader:ImageLoader
    @Inject
    lateinit var mAppManager:AppManager


    override fun onDestroy() {
          super.onDestroy();
    }
}
