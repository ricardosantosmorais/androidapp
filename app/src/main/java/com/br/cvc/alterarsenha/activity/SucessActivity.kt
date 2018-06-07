package com.br.cvc.alterarsenha.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.br.cvc.alterarsenha.R
import kotlinx.android.synthetic.main.activity_sucess.*

class SucessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sucess)

        btn_back.setOnClickListener{ finish() }
    }
}
