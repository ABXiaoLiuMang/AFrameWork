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
package com.test1.mvp.model.api.service;

import com.test1.mvp.model.entity.BaseResponse;
import com.test1.mvp.model.entity.ItemBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

import static com.test1.mvp.model.api.Api.TEST_DOMAIN_NAME;
import static me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER;

/**
 * ================================================
 * 展示 {@link Retrofit#create(Class)} 中需要传入的 ApiService 的使用方式
 * 存放关于 gank 的一些 API
 * ================================================
 */
public interface GankService {
    /**
     * 列表
     */
    @Headers({DOMAIN_NAME_HEADER + TEST_DOMAIN_NAME})
    @GET("/api/data/福利/{num}/{page}")
    Observable<BaseResponse<List<ItemBean>>> getGirlList(@Path("num") int num, @Path("page") int page);
}
