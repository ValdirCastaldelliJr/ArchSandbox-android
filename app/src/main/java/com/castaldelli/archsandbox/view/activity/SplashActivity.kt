package com.castaldelli.archsandbox.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.castaldelli.archsandbox.R
import com.castaldelli.archsandbox.core.CoreActivity
import com.castaldelli.archsandbox.databinding.ActivitySplashBinding
import com.castaldelli.archsandbox.view.ViewRouter
import com.castaldelli.archsandbox.viewmodel.SplashViewModel

class SplashActivity : CoreActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        viewModel.onResponse.observe(this, Observer { ViewRouter.goToMenuActivity(this) })
        viewModel.onFailureMessage.observe(this, Observer {
            Toast.makeText(this@SplashActivity, it , Toast.LENGTH_LONG).show() // FIXME Poderia ser um dialog
        })

        DataBindingUtil.setContentView<ActivitySplashBinding>(this@SplashActivity, R.layout.activity_splash).apply {
            this.lifecycleOwner = this@SplashActivity
            this.vm = viewModel
        }
    }
}