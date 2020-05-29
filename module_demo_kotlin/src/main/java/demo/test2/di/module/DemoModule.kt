package demo.test2.di.module

import com.common.di.scope.ActivityScope

import dagger.Module
import dagger.Provides
import demo.test2.mvp.contract.DemoContract
import demo.test2.mvp.model.DemoModel


@Module
//构建DemoModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter 注意：Module/Model
class DemoModule(private val view: DemoContract.View) {
    @ActivityScope
    @Provides
    fun provideDemoView(): DemoContract.View {
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideDemoModel(model: DemoModel): DemoContract.Model {
        return model
    }

}
