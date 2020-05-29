package demo.test2.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Message
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.common.base.BaseFragment
import com.common.base.DefaultAdapter
import com.common.di.component.AppComponent
import com.common.utils.CommonUtils
import demo.R

import demo.test2.di.component.DaggerTestMvpKTComponent
import demo.test2.di.module.TestMvpKTModule
import demo.test2.mvp.contract.TestMvpKTContract
import demo.test2.mvp.presenter.TestMvpKTPresenter



/**
 * 如果没presenter
 * 你可以这样写
 *
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class TestMvpKTFragment : BaseFragment<TestMvpKTPresenter>() , TestMvpKTContract.View{
    companion object {
    fun newInstance():TestMvpKTFragment {
        val fragment = TestMvpKTFragment()
        return fragment
    }
    }


    override fun setupFragmentComponent(appComponent:AppComponent) {
        DaggerTestMvpKTComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .testMvpKTModule(TestMvpKTModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View{
        return inflater.inflate(R.layout.fragment_test_mvp_kt, container, false);
    }

    override fun initData(savedInstanceState:Bundle?) {

    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    override fun setData(data:Any?) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message:String) {
        CommonUtils.snackbarText(message)
    }

    override fun launchActivity(intent:Intent) {
        CommonUtils.startActivity(intent)
    }

    override fun killMyself() {

    }
}
