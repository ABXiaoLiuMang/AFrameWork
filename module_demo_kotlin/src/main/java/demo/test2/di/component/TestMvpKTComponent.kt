package demo.test2.di.component

import dagger.Component
import com.common.di.component.AppComponent

import demo.test2.di.module.TestMvpKTModule

import com.common.di.scope.ActivityScope
import demo.test2.mvp.ui.activity.TestMvpKTActivity
import demo.test2.mvp.ui.fragment.TestMvpKTFragment


@ActivityScope
@Component(modules = arrayOf(TestMvpKTModule::class),dependencies = arrayOf(AppComponent::class))
interface TestMvpKTComponent {
	fun inject(activity:TestMvpKTActivity)
	fun inject(fragment:TestMvpKTFragment)
}
