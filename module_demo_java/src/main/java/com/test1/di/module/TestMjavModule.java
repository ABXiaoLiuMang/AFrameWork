package com.test1.di.module;

import com.common.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.test1.mvp.contract.TestMjavContract;
import com.test1.mvp.model.TestMjavModel;

@Module
public abstract class TestMjavModule {

    @Binds
    abstract TestMjavContract.Model bindTestMjavModel(TestMjavModel model);
}