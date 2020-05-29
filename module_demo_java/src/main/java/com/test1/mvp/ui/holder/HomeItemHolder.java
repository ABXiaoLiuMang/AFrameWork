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
package com.test1.mvp.ui.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.common.base.BaseHolder;
import com.common.di.component.AppComponent;
import com.common.imageloader.ImageLoader;
import com.common.utils.CommonUtils;
import com.test1.R;
import com.test1.mvp.model.entity.ItemBean;
import com.test1.mvp.ui.activity.TestMjavActivity;

import commonsdk.imgaEngine.config.CommonImageConfigImpl;

/**
 * ================================================
 * <p>
 * 展示 {@link BaseHolder} 的用法
 * <p>
 * ================================================
 */
public class HomeItemHolder extends BaseHolder<ItemBean> {

    ImageView mAvatar;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;//用于加载图片的管理类,默认使用 Glide,使用策略模式,可替换框架

    public HomeItemHolder(View itemView) {
        super(itemView);
        //可以在任何可以拿到 Context 的地方,拿到 AppComponent,从而得到用 Dagger 管理的单例对象
        mAppComponent = CommonUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();

        mAvatar = itemView.findViewById(R.id.iv_avatar);
//        mAvatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

    }

    @Override
    public void setData(ItemBean data, int position) {
        //itemView 的 Context 就是 Activity, Glide 会自动处理并和该 Activity 的生命周期绑定
        if (!TextUtils.isEmpty(data.getUrl())) {

            mImageLoader.loadImage(itemView.getContext(),
                    CommonImageConfigImpl
                            .builder()
                            .url(data.getUrl())
                            .imageView(mAvatar)
                            .build());

//            Glide.with(itemView.getContext())
//                    .load(data.getUrl())
//                    .into(mAvatar);

        } else {
            mAvatar.setImageResource(R.mipmap.gank_ic_logo);
        }
    }

    @Override
    protected void onRelease() {

    }
}
