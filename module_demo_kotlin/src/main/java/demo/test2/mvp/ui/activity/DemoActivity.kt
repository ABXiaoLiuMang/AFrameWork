package demo.test2.mvp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.common.base.BaseActivity
import com.common.di.component.AppComponent
import com.common.utils.CheckROM
import com.common.utils.CommonUtils
import com.common.utils.DeviceUtils
import com.felix.base.model.AccountModel
import commonsdk.core.RouterHub
import commonsdk.ktx.createNotificationChannel
import commonsdk.ktx.ext.toast
import commonsdk.ktx.showNotification
import commonsdk.mmkv.utils.AccountUtils
import demo.R
import demo.test2.di.component.DaggerDemoComponent
import demo.test2.di.module.DemoModule
import demo.test2.mvp.contract.DemoContract
import demo.test2.mvp.presenter.DemoPresenter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_demo.*
import timber.log.Timber

/**
 * ================================================
 * 展示 kotlin View 的用法
 * ================================================
 */
@Route(path = RouterHub.KOTLIN_DEMOACTIVITY)
class DemoActivity : BaseActivity<DemoPresenter>(), DemoContract.View {

    val CHANNEL_ID = "msg_1"

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerDemoComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .demoModule(DemoModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_demo //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @SuppressLint("CheckResult")
    override fun initData(savedInstanceState: Bundle?) {

        mTvVersion.setOnClickListener(View.OnClickListener {
            ARouter.getInstance()
                    .build(RouterHub.KOTLIN_TESTMVPKTACTIVITY)
                    .navigation(this)
        })

        mPresenter?.getHomeData()

        /**
         * mmkv 测试
         */
        val accountModel = AccountModel()
        AccountUtils.cacheAccount(accountModel)
        val acc = AccountUtils.getCacheAccount()
        Timber.e("---------mmkv cacheJson------------" + acc?.id)

        toast("toast")
//        longToast("toast")

        Observable.just("")
                .subscribeOn(Schedulers.io())
                .subscribe {
                    createNotificationChannel(this, CHANNEL_ID, CHANNEL_ID, CHANNEL_ID)
                    showNotification(this, CHANNEL_ID, if (CheckROM.isMiui()) "isMiui" else "other", "R 对阵 N", R.mipmap.ic_launcher)
                }

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        CommonUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        CommonUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }


}