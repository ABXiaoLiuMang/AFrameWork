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
package com.test1.mvp.model;

import com.common.di.scope.ActivityScope;
import com.common.integration.IRepositoryManager;
import com.common.mvp.BaseModel;
import com.test1.mvp.contract.HomeContract;
import com.test1.mvp.model.api.service.GankService;
import com.test1.mvp.model.entity.BaseResponse;
import com.test1.mvp.model.entity.ItemBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * ================================================
 * 展示 Model 的用法
 * ================================================
 */
@ActivityScope
public class GankModel extends BaseModel implements HomeContract.Model {

    @Inject
    public GankModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseResponse<List<ItemBean>>> getGirlList(int num, int page) {
        return mRepositoryManager
                .obtainRetrofitService(GankService.class)
                .getGirlList(num, page);
    }
}
