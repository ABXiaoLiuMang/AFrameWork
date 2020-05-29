package demo.test2.mvp.model

import android.app.Application
import com.google.gson.Gson
import com.common.integration.IRepositoryManager
import com.common.mvp.BaseModel

import com.common.di.scope.ActivityScope
import javax.inject.Inject

import demo.test2.mvp.contract.TestMvpKTContract

@ActivityScope
class TestMvpKTModel
@Inject
constructor(repositoryManager: IRepositoryManager) : BaseModel(repositoryManager), TestMvpKTContract.Model{
    @Inject
    lateinit var mGson:Gson;
    @Inject
    lateinit var mApplication:Application;

    override fun onDestroy() {
          super.onDestroy();
    }
}
