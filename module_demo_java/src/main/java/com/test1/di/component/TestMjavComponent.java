package com.test1.di.component;

import com.common.di.component.AppComponent;
import com.common.di.scope.ActivityScope;
import com.test1.di.module.TestMjavModule;
import com.test1.mvp.contract.TestMjavContract;
import com.test1.mvp.ui.activity.TestMjavActivity;
import com.test1.mvp.ui.fragment.TestMjavFragment;

import dagger.BindsInstance;
import dagger.Component;


@ActivityScope
@Component(modules = TestMjavModule.class, dependencies = AppComponent.class)
public interface TestMjavComponent {
    void inject(TestMjavActivity activity);

    void inject(TestMjavFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        TestMjavComponent.Builder view(TestMjavContract.View view);

        TestMjavComponent.Builder appComponent(AppComponent appComponent);

        TestMjavComponent build();
    }
}