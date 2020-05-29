package com.test1.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.common.integration.IRepositoryManager;
import com.common.mvp.BaseModel;

import com.common.di.scope.ActivityScope;

import javax.inject.Inject;

import com.test1.mvp.contract.TestMjavContract;


@ActivityScope
public class TestMjavModel extends BaseModel implements TestMjavContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public TestMjavModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}