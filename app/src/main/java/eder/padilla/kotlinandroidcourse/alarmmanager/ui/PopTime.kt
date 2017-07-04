package eder.padilla.kotlinandroidcourse.alarmmanager.ui

import android.app.DialogFragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.alarmmanager.AlarmActivity

/**
 * Created by ederpadilla on 18/06/17.
 */
class PopTime : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var myView= inflater!!.inflate(R.layout.pop_time,container,false)


        var buDone=myView.findViewById(R.id.buDone) as Button
        var tp1=myView.findViewById(R.id.tp1) as TimePicker

        buDone.setOnClickListener({
            //Code here
            val ma= activity as AlarmActivity
            if(Build.VERSION.SDK_INT>=23) {
                ma.setTime(tp1.hour, tp1.minute)
            }else{
                ma.setTime(tp1.currentHour, tp1.currentMinute)
            }

            this.dismiss()
        })


        return myView
    }

}