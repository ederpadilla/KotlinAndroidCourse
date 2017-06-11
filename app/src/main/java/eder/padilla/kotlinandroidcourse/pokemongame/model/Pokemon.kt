package eder.padilla.kotlinandroidcourse.pokemongame.model

/**
 * Created by ederpadilla on 11/06/17.
 */
class Pokemon(){

    var name : String? =null
    var des : String? =null
    var image : Int? =null
    var power : Double? =null
    var lat : Double? =null
    var long : Double? =null
    var isCatch : Boolean? = null

    constructor(name : String , des : String , image : Int , power : Double ,lat : Double , long :Double) : this() {
        this.name = name
        this.des = des
        this.image = image
        this.power = power
        this.lat = lat
        this.long = long
        this.isCatch = false

    }

}