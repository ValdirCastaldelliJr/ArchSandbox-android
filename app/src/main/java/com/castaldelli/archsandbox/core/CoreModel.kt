package com.castaldelli.archsandbox.core

import android.util.Log
import com.castaldelli.archsandbox.model.ErrorModel

import java.lang.Exception

open class CoreModel {
    var error: ErrorModel? = null

    fun error(e : Exception) {
        Log.e("ServiceError", e.localizedMessage, e)
        error = ErrorModel(e)
    }

    fun hasError() : Boolean = error != null

}