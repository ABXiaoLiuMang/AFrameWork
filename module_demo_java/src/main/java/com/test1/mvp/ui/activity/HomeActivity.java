/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test1.mvp.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.common.base.BaseActivity;
import com.common.base.DefaultAdapter;
import com.common.di.component.AppComponent;
import com.common.utils.CommonUtils;
import com.common.utils.PermissionUtils;
import com.felix.base.model.AccountModel;
import com.paginate.Paginate;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.test1.R;
import com.test1.db.AppDatabase;
import com.test1.db.entity.User;
import com.test1.db.entity.UserDao;
import com.test1.di.component.DaggerHomeComponent;
import com.test1.mvp.contract.HomeContract;
import com.test1.mvp.presenter.HomePresenter;

import java.util.List;

import javax.inject.Inject;

import commonsdk.core.RouterHub;
import commonsdk.mmkv.utils.AccountUtils;
import timber.log.Timber;

/**
 * ================================================
 * 展示 java View 的用法
 * ================================================
 */
@Route(path = RouterHub.JAVA_HOMEACTIVITY)
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Inject
    RecyclerView.LayoutManager mLayoutManager;
    @Inject
    RecyclerView.Adapter mAdapter;

    private Paginate mPaginate;
    private boolean isLoadingMore;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.gank_activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        topbar();

        initRecyclerView();
        mRecyclerView.setAdapter(mAdapter);

        initPaginate();

        /**
         * room 测试
         * 留意数据库的创建需要的权限
         */
        RxPermissions rxPermissions = new RxPermissions(this);
        PermissionUtils.requestPermissions(new PermissionUtils.RequestPermission() {

                                               @Override
                                               public void onRequestPermissionSuccess() {

                                                   /**
                                                    * room存储伪代码-----------------
                                                    */

                                                   User user = new User();
                                                   user.setUserId("testid");
                                                   user.setName("test");

                                                   UserDao dao = AppDatabase.get(CommonUtils.obtainAppComponentFromContext(getActivity())).userDao();
                                                   dao.insertAll(user);

                                                   List<User> users = dao.getAll();
                                                   Timber.e("---------room--------------" + users.toString());

                                                   /**
                                                    * --------------------------------
                                                    */


                                               }

                                               @Override
                                               public void onRequestPermissionFailure() {
                                                   // 如果失败跳到到应用设置页面

                                               }
                                           }, rxPermissions,
                Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        /**
         * mmkv 测试
         */
        AccountModel accountModel = new AccountModel();
        AccountUtils.INSTANCE.cacheAccount(accountModel);

        AccountModel acc = AccountUtils.INSTANCE.getCacheAccount();
        Timber.e("---------mmkv AccountModel--------------" + acc.getId());

        onRefresh();

    }

    /**
     * 展示topbar的用法
     */
    private void topbar() {
        QMUITopBar mQMUITopBarLayout = findViewById(R.id.topbar);
        //设置标题，默认是显示在中间，如果要控制显示位置请配合setTitleGravity使用
        mQMUITopBarLayout.setTitle("QMUI").setTextColor(ContextCompat.getColor(this, R.color.qmui_config_color_white));
//        View inflate = LayoutInflater.from(this).inflate(R.layout.layout, null);
        //为中间标题设置自己的view
//        mQMUITopBarLayout.setCenterView(inflate);
        //控制标题的显示位置
        mQMUITopBarLayout.setTitleGravity(Gravity.LEFT);
        //设置背景颜色
        mQMUITopBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.public_colorPrimary));
        //设置一级标题 如上图显示，如果设置当前标题那么setTitle将不起作用同理位置由setTitleGravity控制
        mQMUITopBarLayout.setEmojiTitle("Title1");
        //设置二级标题，如上图显示i
        mQMUITopBarLayout.setSubTitle("Title2");
        //是否在左边添加返回按钮，如果不想使用这个想改变返回图标，请使用addLeftImageButton
        mQMUITopBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //为左边按钮设置图标参数1：资源id，参数2：为控件设置id(不知道解释对不对望原谅)：该按钮的 id，
        // 可在ids.xml中找到合适的或新增。手工指定 viewId 是为了适应自动化测试。
        mQMUITopBarLayout.addLeftImageButton(R.mipmap.gank_ic_logo, 0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //是否显示下划线
//        mQMUITopBarLayout.setBackgroundDividerEnabled(true);
        //设置透明度
//        mQMUITopBarLayout.setBackgroundAlpha(198);
    }

    @Override
    public void onRefresh() {
        mPresenter.requestGirls(true);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        CommonUtils.configRecyclerView(mRecyclerView, mLayoutManager);
    }

    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(String message) {
        CommonUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(Intent intent) {
        CommonUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    /**
     * 开始加载更多
     */
    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    /**
     * 结束加载更多
     */
    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestGirls(false);
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecyclerView);
        //super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
        this.mPaginate = null;
    }
}
