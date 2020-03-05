package com.example.third

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var receiver: BroadcastReceiver

    companion object {
        const val ACTION_COUNT="count_action"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView2.movementMethod = ScrollingMovementMethod()
        setReceiver()

    }
    fun onClick(view: View){
        val data = Intent(this,PrimeService::class.java).apply{
            putExtra("Number",editText.text.toString().toInt())
        }
        startService(data)
        button.isEnabled=false
    }

    private fun setReceiver() {
        receiver = NewReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_COUNT)
        registerReceiver(receiver, intentFilter)

    }
    private inner class NewReceiver : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            textView4.text=intent.getIntExtra("Count",0).toString()
            textView2.text=intent.getStringExtra("Prime Numbers")
            button.isEnabled=true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}
