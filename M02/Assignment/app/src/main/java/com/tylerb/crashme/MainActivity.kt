package com.tylerb.crashme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.crashlytics.android.Crashlytics
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = this.localClassName

        null_crash.setOnClickListener {
            breadCrumb(name, "Null button click")
            throw NullPointerException("Null Pointer")

        }

        runtime.setOnClickListener {
            breadCrumb(name, "Runtime button click")
            throw RuntimeException()
        }

        zero.setOnClickListener {
            breadCrumb(name, "Zero button click")
            12/0
        }

        array.setOnClickListener {
            breadCrumb(name, "Array button click")
            val list = emptyList<Int>()
            list[12]
        }


    }
}

fun breadCrumb(activity: String, about: String){
    val crumb = "$activity - $about"
    Crashlytics.log(crumb)
}
