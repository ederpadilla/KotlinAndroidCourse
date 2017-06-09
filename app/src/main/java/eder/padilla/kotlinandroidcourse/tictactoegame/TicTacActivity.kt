package eder.padilla.kotlinandroidcourse.tictactoegame

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

import eder.padilla.kotlinandroidcourse.R

class TicTacActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer =1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac)
    }
    protected fun ticTacToe(view : View){
        val buSelected = view as Button
         var cellId  = 0
        when(buSelected.id){
            R.id.mButton1-> cellId =1
            R.id.mButton2-> cellId =2
            R.id.mButton3-> cellId =3
            R.id.mButton4-> cellId =4
            R.id.mButton5-> cellId =5
            R.id.mButton6-> cellId =6
            R.id.mButton7-> cellId =7
            R.id.mButton8-> cellId =8
            R.id.mButton9-> cellId =9
        }
        playGame(cellId,buSelected)
    }

    fun playGame(cellId : Int, buttonSelected : Button){
        buttonSelected.isEnabled=false
        if (activePlayer==1){
            buttonSelected.text = "x"
            buttonSelected.setBackgroundColor(Color.BLUE)
            player1.add(cellId)
            activePlayer=2
        }else{
            buttonSelected.text = "o"
            buttonSelected.setBackgroundColor(Color.GREEN)
            player2.add(cellId)
            activePlayer=1
        }

        buttonSelected.isEnabled=false
        checkWiner()
    }
    fun checkWiner(){
        var winer = -1
        //raw 1
        if (player1.contains(1)&&player1.contains(2)&&player1.contains(3)){
            winer=1
        }
        if (player2.contains(1)&&player2.contains(2)&&player2.contains(3)){
            winer=2
        }
        //raw 2
        if (player1.contains(4)&&player1.contains(5)&&player1.contains(6)){
            winer=1
        }
        if (player2.contains(4)&&player2.contains(5)&&player2.contains(6)){
            winer=2
        }
        //raw 3
        if (player1.contains(7)&&player1.contains(8)&&player1.contains(9)){
            winer=1
        }
        if (player2.contains(7)&&player2.contains(8)&&player2.contains(9)){
            winer=2
        }
        //colon 1
        if (player1.contains(1)&&player1.contains(4)&&player1.contains(7)){
            winer=1
        }
        if (player2.contains(1)&&player2.contains(4)&&player2.contains(7)){
            winer=2
        }
        //colon 2
        if (player1.contains(2)&&player1.contains(5)&&player1.contains(8)){
            winer=1
        }
        if (player2.contains(2)&&player2.contains(5)&&player2.contains(8)){
            winer=2
        }
        //colo 3
        if (player1.contains(3)&&player1.contains(6)&&player1.contains(9)){
            winer=1
        }
        if (player2.contains(3)&&player2.contains(6)&&player2.contains(9)){
            winer=2
        }
        //diagonal 1
        if (player1.contains(1)&&player1.contains(5)&&player1.contains(9)){
            winer=1
        }
        if (player2.contains(1)&&player2.contains(5)&&player2.contains(9)){
            winer=2
        }
        //diagonal 2
        if (player1.contains(3)&&player1.contains(5)&&player1.contains(7)){
            winer=1
        }
        if (player2.contains(3)&&player2.contains(5)&&player2.contains(7)){
            winer=2
        }

        if (winer!= -1){
            if (winer==1){
                Toast.makeText(this,"jugador 1 gano el juego",Toast.LENGTH_SHORT).show()
            }else if (winer==2){
                Toast.makeText(this,"jugador 2 gano el juego",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
