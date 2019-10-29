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
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var disposable: Disposable
    lateinit var disposable2: Disposable

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

        val retrofit = Retrofit.Builder()
            .baseUrl("http://qrng.anu.edu.au/API/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val call = retrofit.create(Api::class.java)

        disposable2 = call.getNum("4", "uint16")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {numbers ->
                    val list = numbers.data.toIntArray()
                    list.sort()
                    val rateD = list[0] / 1000.toDouble()
                    amount.setText("${list[3] * 10}")
                    payment.setText("${list[2]}")
                    rate.setText("$rateD")
                    period.setText("${list[1] / 1000}")

                },
                {fail -> println(fail)}
            )







    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
        disposable2.dispose()
    }

}
