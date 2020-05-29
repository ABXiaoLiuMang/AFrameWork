package com.test1.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.common.base.BaseActivity;
import com.common.di.component.AppComponent;
import com.common.utils.CommonUtils;
import com.test1.R;
import com.test1.di.component.DaggerTestMjavComponent;
import com.test1.mvp.contract.TestMjavContract;
import com.test1.mvp.presenter.TestMjavPresenter;
import com.test1.mvp.ui.fragment.TestMjavFragment;

import commonsdk.core.RouterHub;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static com.common.utils.Preconditions.checkNotNull;

@Route(path = RouterHub.JAVA_TESTMJAVACTIVITY)
public class TestMjavActivity extends BaseActivity<TestMjavPresenter> implements TestMjavContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTestMjavComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_test_mjav; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (findFragment(TestMjavFragment.class) == null) {
            loadRootFragment(R.id.fl_container, TestMjavFragment.newInstance());
        }

        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity优先滑动退出;  false: Fragment优先滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
    }

    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        CommonUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        CommonUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
