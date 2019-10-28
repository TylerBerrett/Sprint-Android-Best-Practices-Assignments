package com.example.mortgage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsAmount = amount.textChanges()
            .filter { it.length > 1 }

        val obsPayment = payment.textChanges()
            .filter { it.length > 1 }

        val obsRate = rate.textChanges()
            .filter { it.length > 1 }

        val obsPeriod = period.textChanges()
            .filter { it.length > 1 }

        val combined = Observables.combineLatest(obsAmount, obsPayment, obsRate, obsPeriod) { obsAmount, obsPayment, obsRate, obsPeriod ->
            val amount = obsAmount.toString().toDouble()
            val payment = obsPayment.toString().toDouble()
            val rate = obsRate.toString().toDouble()
            val period = obsPeriod.toString().toDouble()
            val p = amount - payment
            val r = (rate / 100) / 12
            val n = period * 12


            val first = r * (1 + r).pow(360)
            val sec = (1+r).pow(n) - 1
            val calc = p * ( first / sec )

            val format = calc.toString().split(".")
            val print = format[0]

            "$$print per month"
        }

        disposable = combined.observeOn(AndroidSchedulers.mainThread()).subscribe {calc -> display.text = calc}




    }
}
