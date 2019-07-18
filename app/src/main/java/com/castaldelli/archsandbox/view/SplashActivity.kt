package com.castaldelli.archsandbox.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.castaldelli.archsandbox.R
import com.castaldelli.archsandbox.core.CoreViewActivity
import com.castaldelli.archsandbox.databinding.SplashActivityBinding
import com.castaldelli.archsandbox.viewmodel.SplashViewModel

class SplashActivity : CoreViewActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)


        DataBindingUtil.setContentView<SplashActivityBinding>(this@SplashActivity, R.layout.splash_activity).apply {
            this.lifecycleOwner = this@SplashActivity
            this.vm = viewModel
        }

        viewModel.onResponse.observe(this, Observer {
            startActivity(Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_NEW_TASK)
                addFlags(FLAG_ACTIVITY_CLEAR_TASK)
            })
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        })

        viewModel.onFailureMessage.observe(this, Observer {
            Toast.makeText(this@SplashActivity, it , Toast.LENGTH_LONG).show() // FIXME Poderia ser um dialog
        })
    }
}