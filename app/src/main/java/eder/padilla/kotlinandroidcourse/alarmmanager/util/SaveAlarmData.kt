package eder.padilla.kotlinandroidcourse.alarmmanager.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import eder.padilla.kotlinandroidcourse.Util
import java.util.*

/**
 * Created by ederpadilla on 18/06/17.
 */
class SaveAlarmData{
    var context : Context? = null

    var sharedPreferences : SharedPreferences? =null

    constructor(context: Context){
        this.context = context
        this.sharedPreferences=context.getSharedPreferences("AlarmReference",Context.MODE_PRIVATE)
    }
    fun setAlarm(){
        var hour = getHour()
        var minutes = getMinute()
        Util.log("Entra al save alarm")
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,hour)
        calendar.set(Calendar.MINUTE,minutes)
        calendar.set(Calendar.SECOND,0)
        var intent=Intent(context, MyBroadcastReceiver::class.java)
        intent.putExtra("message","alarm time")
        intent.action="com.tester.alarmmanager"
        val am = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        am.setRepeating(AlarmManager.RTC,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
    }
    fun saveDataInSharedPreference(hour : Int , minute : Int){
        var editor = sharedPreferences!!.edit()
        editor.putInt("hour",hour)
        editor.putInt("minutes",minute)
        editor.commit()

    }
    fun getHour(): Int{
        return sharedPreferences!!.getInt("hour",0)
    }
    fun getMinute(): Int{
        return sharedPreferences!!.getInt("minutes",0)
    }
}