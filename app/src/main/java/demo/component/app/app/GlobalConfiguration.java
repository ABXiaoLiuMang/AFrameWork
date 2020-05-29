/*
 *
 *
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
package demo.component.app.app;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.common.base.delegate.ActivityDelegate;
import com.common.base.delegate.AppLifecycles;
import com.common.di.module.GlobalConfigModule;
import com.common.integration.ActivityLifecycle;
import com.common.integration.ConfigModule;
import com.common.utils.CommonUtils;

import java.util.List;

import commonsdk.core.ActivityLifecycleCallbacksImpl;

/**
 * ================================================
 * 组件的全局配置信息在此配置, 需要将此实现类声明到 AndroidManifest 中
 * CommonSDK 中已有 {@link commonsdk.core.GlobalConfiguration} 配置有组件可公用的配置信息
 * 这里用来配置一些组件自身私有的配置信息
 * ================================================
 */
public final class GlobalConfiguration implements ConfigModule {

    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
    //使用 builder 可以为框架配置一些配置信息
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        //向 Application的 生命周期中注入一些自定义逻辑
        // AppLifecycles 的所有方法都会在基类 Application 的对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        // 可以根据不同的逻辑添加多个实现类
        lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        //向 Activity 的生命周期中注入一些自 定义逻辑

    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        //向 Fragment 的生命周期中注入一些自定义逻辑
//        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
//                ((RefWatcher) CommonUtils
//                        .obtainAppComponentFromContext(f.getActivity())
//                        .extras()
//                        .get(RefWatcher.class.getName()))
//                        .watch(f);
//            }
//        });
    }

}
