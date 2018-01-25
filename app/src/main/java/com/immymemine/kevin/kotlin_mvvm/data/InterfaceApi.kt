package com.immymemine.kevin.kotlin_mvvm.data

import com.immymemine.kevin.kotlin_mvvm.data.model.SearchItem
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by quf93 on 2018-01-24.
 */
interface InterfaceApi {
    @GET("loadClass/{id}")
    fun search(id : String) : Observable<List<SearchItem>>
}