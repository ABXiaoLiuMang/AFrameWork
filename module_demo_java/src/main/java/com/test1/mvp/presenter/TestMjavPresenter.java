package com.test1.mvp.presenter;

import android.app.Application;

import com.common.di.scope.ActivityScope;
import com.common.imageloader.ImageLoader;
import com.common.integration.AppManager;
import com.common.mvp.BasePresenter;
import com.test1.mvp.contract.TestMjavContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class TestMjavPresenter extends BasePresenter<TestMjavContract.Model, TestMjavContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public TestMjavPresenter(TestMjavContract.Model model, TestMjavContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}
