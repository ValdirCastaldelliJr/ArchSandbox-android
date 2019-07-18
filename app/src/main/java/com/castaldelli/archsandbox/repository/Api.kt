package com.castaldelli.archsandbox.repository


import com.castaldelli.archsandbox.model.SomeModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("some/service/path")
    fun getSomethingFromBackEnd2() : Deferred<Response<SomeModel>>


}