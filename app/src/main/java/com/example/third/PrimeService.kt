
package com.example.third

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log


class PrimeService : IntentService("PrimeService") {

    override fun onHandleIntent(intent: Intent?) {
        val number: Int = intent?.getIntExtra("Number",0) ?: 0
        Log.e("My number",number.toString()+"this")
        var list= getPrimeNumbers(number)
        val data=Intent().apply{
            action = MainActivity.ACTION_COUNT
            putExtra("Count", list.size)
            putExtra("Prime Numbers", list.joinToString())
        }

        sendBroadcast(data)
    }

    private fun getPrimeNumbers (number: Int): MutableList<Int>{
        val list= mutableListOf<Int>()
        for(i in 1..number){
            if(isPrime(i)){
                list.add(i)
            }

        }

        return list
    }
    private fun isPrime(number: Int) : Boolean{
        for(i in 2..kotlin.math.sqrt(number.toDouble()).toInt()) {
            if (number % i == 0)
                return false
        }
        return true
    }


}
