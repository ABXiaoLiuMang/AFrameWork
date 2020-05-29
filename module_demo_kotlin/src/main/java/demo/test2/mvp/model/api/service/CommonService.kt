package com.yangyan.xxp.yangyannew.mvp.model.service

import demo.test2.mvp.model.api.Api.TEST_DOMAIN_NAME
import demo.test2.mvp.model.entity.ImagesInfo
import io.reactivex.Observable
import me.jessyan.retrofiturlmanager.RetrofitUrlManager.DOMAIN_NAME_HEADER
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Description :api
 */
interface CommonService {

    @Headers(DOMAIN_NAME_HEADER + TEST_DOMAIN_NAME)
    @GET("{key}")
    fun getImagesByKey(@Path("key")tag:String, @Query("page") page: Int):Observable<List<ImagesInfo>>

}