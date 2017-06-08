package eder.padilla.kotlinandroidcourse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    protected fun getAgeEvent(view: View){
        val age : String= mEtGetAge.text.toString()
        val year : Int = Calendar.getInstance().get(Calendar.YEAR)
        val myAge = year-(Integer.valueOf(age))
        mTvAge.text = "Tu edad es $myAge"
    }
}
