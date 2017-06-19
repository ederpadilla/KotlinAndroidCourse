package eder.padilla.kotlinandroidcourse.alarmmanager.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import eder.padilla.kotlinandroidcourse.Util

/**
 * Created by ederpadilla on 18/06/17.
 */
class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Util.log("Entra al broadcast")
        if (intent!!.action.equals("com.tester.alarmmanager")){
            Util.log("Entra al intent")
            var bundle = intent.extras
            val notifyMe = Notifications()
            notifyMe.Notify(context!!,bundle.getString("message"),10)
            //Toast.makeText(context,bundle.getString("message"),Toast.LENGTH_SHORT).show()
        }else if(intent!!.action.equals("android.intent.action.BOOT_COMPLETED")){
            val saveData = SaveAlarmData(context!!)
            saveData.setAlarm()


        }
    }

}