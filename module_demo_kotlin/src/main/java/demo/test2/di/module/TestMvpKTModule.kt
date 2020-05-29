package demo.test2.di.module

import com.common.di.scope.ActivityScope

import dagger.Module
import dagger.Provides

import demo.test2.mvp.contract.TestMvpKTContract
import demo.test2.mvp.model.TestMvpKTModel


@Module
 //构建TestMvpKTModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
class TestMvpKTModule(private val view:TestMvpKTContract.View) {
    @ActivityScope
    @Provides
    fun provideTestMvpKTView():TestMvpKTContract.View{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideTestMvpKTModel(model:TestMvpKTModel):TestMvpKTContract.Model{
        return model
    }
}
