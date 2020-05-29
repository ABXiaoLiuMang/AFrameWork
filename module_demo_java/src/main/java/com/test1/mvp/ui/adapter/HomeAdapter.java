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
package com.test1.mvp.ui.adapter;

import android.view.View;

import com.common.base.BaseHolder;
import com.common.base.DefaultAdapter;
import com.test1.R;
import com.test1.mvp.model.entity.ItemBean;
import com.test1.mvp.ui.holder.HomeItemHolder;

import java.util.List;

/**
 * ================================================
 * 展示 {@link DefaultAdapter} 的用法
 * ================================================
 */
public class HomeAdapter extends DefaultAdapter<ItemBean> {
    public HomeAdapter(List<ItemBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<ItemBean> getHolder(View v, int viewType) {
        return new HomeItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.gank_recycle_list;
    }
}
