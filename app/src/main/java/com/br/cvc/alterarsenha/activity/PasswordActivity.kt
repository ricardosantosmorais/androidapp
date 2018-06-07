package com.br.cvc.alterarsenha.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_password.*
import android.text.Editable
import android.text.TextWatcher
import com.br.cvc.alterarsenha.R
import com.br.cvc.alterarsenha.service.PasswordService
import java.lang.Double.parseDouble


class PasswordActivity : AppCompatActivity() {

    private var mPassTask: PasswordTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        password2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val passwordStr1 = password1.text.toString()
                val passwordStr2 = password2.text.toString()

                if(validForm(passwordStr1, passwordStr2, false)) {
                    btn_submit.isEnabled = true
                }
            }
            override fun afterTextChanged(s: Editable?) { }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        btn_submit.setOnClickListener { funClick() }
    }

    private fun funClick() {

        if (mPassTask != null) {
            return
        }

        val passwordStr1 = password1.text.toString()
        val passwordStr2 = password2.text.toString()

        if(validForm(passwordStr1, passwordStr2, true)) {
            showProgress(true)
            mPassTask = PasswordTask(passwordStr1, passwordStr2)
            mPassTask!!.execute(null as Void?)
        }
    }

    private fun validForm(passwordStr1: String, passwordStr2: String, error: Boolean): Boolean {

        var response = true

        // Reset errors.
        password1.error = null
        password2.error = null

        var focusView: View? = null

        // Checar se a senha digitada é válida
        if (!TextUtils.isEmpty(passwordStr1) && !isPasswordValid(passwordStr1) && !containsDigit(passwordStr1)) {
            if(error) {
                password1.error = getString(R.string.error_invalid_password)
            }
            focusView = password1
            response = false
        }
        // Chegar se as senhas digitadas são iguais
        else if (passwordStr1 != passwordStr2) {
            if(error) {
                password2.error = getString(R.string.error_incorrect_password)
            }
            focusView = password2
            response = false
        }

        if (!response && error) {
            focusView?.requestFocus()
        }

        return response

    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length >= 6
    }

    private fun containsDigit(s: String?): Boolean {
        var containsDigit = true

        if (s != null && !s.isEmpty()) {
            for (c in s.toCharArray()) {
                try {
                    val num = parseDouble(c.toString())
                    containsDigit = true
                    break
                } catch (e: NumberFormatException) {
                    containsDigit = false
                }
            }
        }

        return containsDigit
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 0 else 1).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_form.visibility = if (show) View.GONE else View.VISIBLE
                        }
                    })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                    .setDuration(shortAnimTime)
                    .alpha((if (show) 1 else 0).toFloat())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            login_progress.visibility = if (show) View.VISIBLE else View.GONE
                        }
                    })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    inner class PasswordTask internal constructor(private val mPassword1: String, private val mPassword2: String) : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {

            try {
                Thread.sleep(2000)
                val service = PasswordService()
                return service.Reset("1000", mPassword1);
            } catch (e: InterruptedException) {
                return false
            }
        }

        override fun onPostExecute(success: Boolean?) {
            mPassTask = null
            showProgress(false)

            if (success!!) {
                val i = Intent(applicationContext, SucessActivity::class.java)
                this@PasswordActivity.startActivity(i)
            } else {
                Snackbar.make(
                        root_layout, // Parent view
                        getString(R.string.error_post), // Message to show
                        Snackbar.LENGTH_LONG // How long to display the message.
                ).show()
            }
        }

        override fun onCancelled() {
            mPassTask = null
            showProgress(false)
        }
    }

}
