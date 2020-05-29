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
package com.test1.di.module;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.base.DefaultAdapter;
import com.common.di.scope.ActivityScope;
import com.test1.mvp.contract.HomeContract;
import com.test1.mvp.model.GankModel;
import com.test1.mvp.model.entity.ItemBean;
import com.test1.mvp.ui.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import commonsdk.core.RouterHub;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * ================================================
 * 展示 Module 的用法
 * ================================================
 */
@Module
public abstract class HomeModule {
    @Binds
    abstract HomeContract.Model bindGankModel(GankModel model);

    @ActivityScope
    @Provides
    static RecyclerView.LayoutManager provideLayoutManager(HomeContract.View view) {
        return new GridLayoutManager(view.getActivity(), 5);
    }

    @ActivityScope
    @Provides
    static List<ItemBean> provideList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    static RecyclerView.Adapter provideGankHomeAdapter(HomeContract.View hview, List<ItemBean> list) {
        HomeAdapter adapter = new HomeAdapter(list);
        adapter.setOnItemClickListener(new DefaultAdapter.OnRecyclerViewItemClickListener<ItemBean>() {
            @Override
            public void onItemClick(View view, int viewType, ItemBean data, int position) {
                ARouter.getInstance()
                        .build(RouterHub.JAVA_TESTMJAVACTIVITY)
//                        .withInt(ZhihuConstants.DETAIL_ID, data.getId())
//                        .withString(ZhihuConstants.DETAIL_TITLE, data.getTitle())
                        .navigation(hview.getActivity());
            }
        });
        return adapter;
    }
}
