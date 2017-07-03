package eder.padilla.kotlinandroidcourse.simplegetage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import eder.padilla.kotlinandroidcourse.R
import kotlinx.android.synthetic.main.activity_get_my_age.*
import java.util.*

class GetMyAgeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_my_age)
    }
    private fun getAgeEvent(view: View){
        val age : String= mEtGetAge.text.toString()
        val year : Int = Calendar.getInstance().get(Calendar.YEAR)
        val myAge = year-(Integer.valueOf(age))
        mTvAge.text = "Tu edad es $myAge"
    }
}
