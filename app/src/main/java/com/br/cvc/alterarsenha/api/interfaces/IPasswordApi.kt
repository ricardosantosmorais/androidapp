package com.br.cvc.alterarsenha.api.interfaces

import com.br.cvc.alterarsenha.api.model.PasswordModelApi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IPasswordApi {

    @POST("resetPassword")
    fun Reset(@Body like: PasswordModelApi): Call<PasswordModelApi>

}