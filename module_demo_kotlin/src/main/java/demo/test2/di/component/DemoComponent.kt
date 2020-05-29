package demo.test2.di.component

import com.common.di.component.AppComponent
import com.common.di.scope.ActivityScope
import dagger.Component
import demo.test2.di.module.DemoModule
import demo.test2.mvp.ui.activity.DemoActivity

@ActivityScope
//@Component(modules = [DemoModel::class], dependencies = [AppComponent::class])
@Component(modules = arrayOf(DemoModule::class), dependencies = arrayOf(AppComponent::class))
interface DemoComponent {
    fun inject(activity: DemoActivity)
}
