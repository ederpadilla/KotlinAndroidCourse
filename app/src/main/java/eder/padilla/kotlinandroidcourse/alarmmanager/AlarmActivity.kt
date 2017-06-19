package eder.padilla.kotlinandroidcourse.alarmmanager

import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.Util
import eder.padilla.kotlinandroidcourse.alarmmanager.ui.PopTime
import eder.padilla.kotlinandroidcourse.alarmmanager.util.SaveAlarmData
import kotlinx.android.synthetic.main.activity_alarm.*

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        val saveData=SaveAlarmData(applicationContext)

        tvShowTime.text= saveData.getHour().toString() + ":" + saveData.getMinute().toString()
    }

    fun setTime(hours : Int , minutes :Int){
        tvShowTime.text= "${hours.toString()} : ${minutes.toString()}"

        val saveData=SaveAlarmData(applicationContext)
        saveData.saveDataInSharedPreference(hours,minutes)
        saveData.setAlarm()
    }

    fun buSetTime(view : View){
        val popTime = PopTime()
        val fm = fragmentManager
        popTime.show(fm,"Select Time")
    }
}
