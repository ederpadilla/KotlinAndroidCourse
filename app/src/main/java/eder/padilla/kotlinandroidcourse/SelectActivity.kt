package eder.padilla.kotlinandroidcourse

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import eder.padilla.kotlinandroidcourse.accelerometer.AccelerometerActivity
import eder.padilla.kotlinandroidcourse.alarmmanager.AlarmActivity
import eder.padilla.kotlinandroidcourse.calculator.CalculatorActivity
import eder.padilla.kotlinandroidcourse.citysunrise.CitySunriseActivity
import eder.padilla.kotlinandroidcourse.lightsensor.LightSensorActivity
import eder.padilla.kotlinandroidcourse.mediaplayer.MediaPlayerActivity
import eder.padilla.kotlinandroidcourse.noteapp.ui.NoteActivity
import eder.padilla.kotlinandroidcourse.pokemongame.PokemonGameTest
import eder.padilla.kotlinandroidcourse.simplegetage.GetMyAgeActivity
import eder.padilla.kotlinandroidcourse.tictactoegame.TicTacActivity
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)
    }

    fun goToAcelerometer(view : View){
        val intent=  Intent(this, AccelerometerActivity::class.java)
        startActivity(intent)
    }
    fun goToCalculator(view : View){
        val intent=  Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }

    fun goToAlarmManager(view : View){
        val intent=  Intent(this, AlarmActivity::class.java)
        startActivity(intent)
    }

    fun goToCitySunrise(view : View){
        val intent=  Intent(this, CitySunriseActivity::class.java)
        startActivity(intent)
    }

    fun goToLightSensor(view : View){
        val intent=  Intent(this, LightSensorActivity::class.java)
        startActivity(intent)
    }

    fun goToMediaPlayer(view : View){
        val intent=  Intent(this, MediaPlayerActivity::class.java)
        startActivity(intent)
    }

    fun goToNoteActivity(view : View){
        val intent=  Intent(this, NoteActivity::class.java)
        startActivity(intent)
    }

    fun goToPokemon(view : View){
        val intent=  Intent(this, PokemonGameTest::class.java)
        startActivity(intent)
    }

    fun goToGetMyAge(view : View){
        val intent=  Intent(this, GetMyAgeActivity::class.java)
        startActivity(intent)
    }

    fun goToTicTacToe(view : View){
        val intent=  Intent(this, TicTacActivity::class.java)
        startActivity(intent)
    }

}
