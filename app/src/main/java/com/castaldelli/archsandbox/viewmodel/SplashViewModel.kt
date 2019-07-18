package com.castaldelli.archsandbox.viewmodel

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.castaldelli.archsandbox.core.CoreViewModel
import com.castaldelli.archsandbox.model.SomeModel
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.lang.Runnable

class SplashViewModel: CoreViewModel() {

    private val _loadingStatus = MutableLiveData<String>()
    val loadingStatus: LiveData<String>
        get() = _loadingStatus

    private var count = 0
    private var strike : Int = 1
    private val handler = Handler()
    private val runnable = Runnable { loop() }

    init {
        count = 0
        handler.postDelayed(runnable, 1000) // FIXME: Remover. Só pra simular algum processamento de splash
    }

    // metodo para simular algum processamento da VM com um loop convencional
    private fun loop() {
        when (count) {
            0 -> {
                _loadingStatus.value = "Verificando raios catódicos..."

                // (...) code something ...
                handler.postDelayed(runnable, 300)  // FIXME: Remover. Só pra simular algum processamento de splash
            }
            1 -> {
                _loadingStatus.value = "Analizando capacitor de fluxo..."

                // (...) code something ...
                handler.postDelayed(runnable, 300) // FIXME: Remover. Só pra simular algum processamento de splash
            }
            2 -> {
                _loadingStatus.value = "Preparando estrela da morte para disparo..."

                // (...) code something ...
                handler.postDelayed(runnable, 300) // FIXME: Remover. Só pra simular algum processamento de splash
            }
            else ->  {
                strike = 1
                getTheThingsDone() // Sync //
            }

        }
        count++
    }

    // Exemplo de loop com coroutines na VM com um try Again em caso de erro
    private fun getTheThingsDone() {

        _loadingStatus.value = "Lock'n'load... strike $strike"

        // Async //
        GlobalScope.launch(Main) {
            val model = getSomethingFromServer()
            if (model.hasError()) {
                delay(5000) // FIXME Try again
                getTheThingsDone()
            } else {
                _loadingStatus.value = "Tango Down!"
                delay(2000) // FIXME: Remover. Só pra simular algum processamento de splash (dar tempo de aparecer Tango down :))
                _onResponse.value = model // código efetivo de envio da info para a tela via LiveData
            }
            strike++
        }
    }


    // Async //
    private suspend fun getSomethingFromServer(): SomeModel {
        return try {
            service.getSomethingFromBackEnd2().await().body()!!
        } catch (e : Exception) {
            SomeModel().apply { error(e) }
        }
    }
}