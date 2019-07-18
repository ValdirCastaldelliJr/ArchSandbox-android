package com.castaldelli.archsandbox.model
import com.castaldelli.archsandbox.core.CoreModel
import com.google.gson.annotations.SerializedName

data class SomeModel (
    @SerializedName("msg") val msg: String? = null
) : CoreModel()
