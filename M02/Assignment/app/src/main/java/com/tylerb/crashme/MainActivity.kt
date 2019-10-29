package com.tylerb.crashme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NullPointerException
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        null_crash.setOnClickListener {
            throw NullPointerException("Null Pointer")
        }

        runtime.setOnClickListener {
            throw RuntimeException()
        }

        zero.setOnClickListener {
            12/0
        }

        array.setOnClickListener {
            val list = emptyList<Int>()
            list[12]
        }


    }
}
