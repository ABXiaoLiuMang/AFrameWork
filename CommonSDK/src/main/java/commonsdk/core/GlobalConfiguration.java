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
package commonsdk.core;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.common.base.delegate.AppLifecycles;
import com.common.di.module.ClientModule;
import com.common.di.module.GlobalConfigModule;
import com.common.http.log.RequestInterceptor;
import com.common.integration.ConfigModule;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import commonsdk.BuildConfig;
import commonsdk.http.Api;
import commonsdk.http.SSLSocketClient;
import commonsdk.imgaEngine.Strategy.CommonGlideImageLoaderStrategy;
import commonsdk.mmkv.utils.MMKVUtils;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import timber.log.Timber;


/**
 * ================================================
 * CommonSDK 的 GlobalConfiguration 含有有每个组件都可公用的配置信息,
 * 每个组件的 AndroidManifest 都应该声明此 ConfigModule
 */
public class GlobalConfiguration implements ConfigModule {

    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        //使用 builder 可以为框架配置一些配置信息
        if (!BuildConfig.LOG_DEBUG) //Release 时,让框架不再打印 Http 请求和响应的信息
            builder.printHttpLogLevel(RequestInterceptor.Level.NONE);

        builder.baseurl(Api.APP_DOMAIN)
                .imageLoaderStrategy(new CommonGlideImageLoaderStrategy())
                .globalHttpHandler(new GlobalHttpHandlerImpl(context))
                .responseErrorListener(new ResponseErrorListenerImpl())
                .gsonConfiguration((context1, gsonBuilder) -> {//这里可以自己自定义配置Gson的参数
                    gsonBuilder
                            .serializeNulls()//支持序列化null的参数
                            .enableComplexMapKeySerialization();//支持将序列化key为object的map,默认只能序列化key为string的map
                })
                .okhttpConfiguration(new ClientModule.OkhttpConfiguration() {
                    @Override
                    public void configOkhttp(Context context, OkHttpClient.Builder builder) {

                        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory(), SSLSocketClient.getTrustManager());
                        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());

                        //让 Retrofit 同时支持多个 BaseUrl 以及动态改变 BaseUrl.
                        RetrofitUrlManager.getInstance().with(builder);

                        builder.addNetworkInterceptor(new StethoInterceptor());//抓包

                    }
                })
                .rxCacheConfiguration((context1, rxCacheBuilder) -> {
                    //这里可以自己自定义配置RxCache的参数
                    rxCacheBuilder.useExpiredDataIfLoaderNotAvailable(true);
                    return null;
                });
    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        //向 Application的 生命周期中注入一些自定义逻辑
        // AppDelegate.Lifecycle 的所有方法都会在基类Application对应的生命周期中被调用,所以在对应的方法中可以扩展一些自己需要的逻辑
        lifecycles.add(new AppLifecycles() {

            @Override
            public void attachBaseContext(@NonNull Context base) {
            }

            @Override
            public void onCreate(@NonNull Application application) {

                /**
                 * debug模式下的配置
                 */
                if (BuildConfig.LOG_DEBUG) {

                    //Timber日志打印
                    Timber.plant(new Timber.DebugTree());
//                    ButterKnife.setDebug(true);

                    ARouter.openLog();     // 打印日志
                    ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

                    RetrofitUrlManager.getInstance().setDebug(true);

                }

                ARouter.init(application); // 尽可能早,推荐在Application中初始化

                /**初始化 MMKV**/
                try {
                    MMKVUtils.init(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onTerminate(@NonNull Application application) {

            }
        });
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        //向 Activity 的生命周期中注入一些自定义逻辑
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        //向 Fragment 的生命周期中注入一些自定义逻辑
        lifecycles.add(new FragmentLifecycleCallbacksImpl());
    }
}
