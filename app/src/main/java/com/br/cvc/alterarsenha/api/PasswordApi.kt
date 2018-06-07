package com.br.cvc.alterarsenha.api

import android.os.StrictMode

import com.br.cvc.alterarsenha.api.interfaces.IPasswordApi
import com.br.cvc.alterarsenha.api.model.PasswordModelApi

import java.io.IOException

class PasswordApi {

    fun Reset(model: PasswordModelApi): Boolean {

        val service: IPasswordApi = BaseApi.client!!.create(IPasswordApi::class.java)

        var resp = false

        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val response = service.Reset(model).execute()

            if (response.isSuccessful) {
                resp = true
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return resp

    }

}
