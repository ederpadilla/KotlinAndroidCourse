package eder.padilla.kotlinandroidcourse.lightsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.Util
import kotlinx.android.synthetic.main.activity_media_player.*
class LightSensorActivity : AppCompatActivity(), SensorEventListener{
    var sensor : Sensor? = null
    var sensorManager : SensorManager ? = null
    var mediaPlayer : MediaPlayer ? = null
    var isRunning = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_sensor)
        initSensor()
    }
    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
    private fun initSensor() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
    override fun onSensorChanged(event: SensorEvent?) {
        Util.log("Value of event cero ${event!!.values[0]}")
        if (event!!.values[0]>40 && !isRunning){
            playMusic()
        }
    }
    private fun playMusic() {
        isRunning = true
        mediaPlayer = MediaPlayer()
        try {
            mediaPlayer!!.setDataSource("http://server6.mp3quran.net/thubti/001.mp3")
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (ex: Exception) {
        Util.log("Error ${ex.localizedMessage}")
        }
    }
}
