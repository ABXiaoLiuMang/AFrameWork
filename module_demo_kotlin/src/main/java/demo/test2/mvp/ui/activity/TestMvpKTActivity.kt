package demo.test2.mvp.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.common.base.BaseActivity
import com.common.di.component.AppComponent
import com.common.utils.CommonUtils
import commonsdk.core.RouterHub
import demo.R
import demo.test2.di.component.DaggerTestMvpKTComponent
import demo.test2.di.module.TestMvpKTModule
import demo.test2.mvp.contract.TestMvpKTContract
import demo.test2.mvp.presenter.TestMvpKTPresenter
import demo.test2.mvp.ui.adapter.PullToRefreshAdapter


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */

@Route(path = RouterHub.KOTLIN_TESTMVPKTACTIVITY)
class TestMvpKTActivity : BaseActivity<TestMvpKTPresenter>(), TestMvpKTContract.View, BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var pullToRefreshAdapter: PullToRefreshAdapter
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onLoadMoreRequested() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRefresh() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerTestMvpKTComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .testMvpKTModule(TestMvpKTModule(this))
                .build()
                .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_test_mvp_kt //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    override fun initData(savedInstanceState: Bundle?) {

        mRecyclerView = findViewById(R.id.rv_list)
        mSwipeRefreshLayout = findViewById(R.id.swipeLayout)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        pullToRefreshAdapter = PullToRefreshAdapter()
        pullToRefreshAdapter.setOnLoadMoreListener(this, mRecyclerView)
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT)
        //        pullToRefreshAdapter.setPreLoadNumber(3);
        mRecyclerView.adapter = pullToRefreshAdapter

        mRecyclerView.addOnItemTouchListener(object : OnItemClickListener() {
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {

            }
        })

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
