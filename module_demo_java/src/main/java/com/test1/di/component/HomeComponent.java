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
package com.test1.di.component;

import com.common.di.component.AppComponent;
import com.common.di.scope.ActivityScope;
import com.test1.di.module.HomeModule;
import com.test1.mvp.contract.HomeContract;
import com.test1.mvp.ui.activity.HomeActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * ================================================
 * 展示 Component 的用法
 * ================================================
 */
@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder view(HomeContract.View view);
        Builder appComponent(AppComponent appComponent);
        HomeComponent build();
    }
}
