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
package demo.test2.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.common.base.delegate.AppLifecycles;

import demo.test2.mvp.model.api.Api;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>

 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

        //使用 RetrofitUrlManager 切换 BaseUrl
        RetrofitUrlManager.getInstance().putDomain(Api.TEST_DOMAIN_NAME, Api.TEST_DOMAIN);

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}