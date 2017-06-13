package eder.padilla.kotlinandroidcourse.pokemongame

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.Util
import eder.padilla.kotlinandroidcourse.pokemongame.model.Pokemon

class PokemonGameActivity : FragmentActivity(), OnMapReadyCallback {
    //WORK WITH USER LOCATION
    private var mMap: GoogleMap? = null

    var location : Location? = null

    private val LOCATION_REQUEST_CODE = 1

    var oldLocation : Location? = null

    var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_game)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        loadPokemon()
        getPermission()
        getUserLocation()
    }

    private fun getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) run { /*mMap!!.setMyLocationEnabled(true)*/ } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_REQUEST_CODE)
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.aubergine_style_json))

            if (!success) {
            }
        } catch (e: Resources.NotFoundException) {
        }

        mMap = googleMap

    }

    fun getUserLocation(){
        var myLocation = MyPersonalLocationListener()
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,3f,myLocation)
        //var myThread = myThread()
        //myThread.start()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.size > 0 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                // mMap!!.setMyLocationEnabled(true) Aqui ponemos lo que se realize cuando se tienen los permisos

            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show()
            }

        }
    }

    fun loadPokemon(){
        pokemonList.add(Pokemon(R.drawable.charmander,"Charmander","char char",30.0,19.370585,-99.157695))
        pokemonList.add(Pokemon(R.drawable.bulbasaur,"Bulbasaur","buuuulbasaaaur",20.0,19.370585,-99.157695))
        pokemonList.add(Pokemon(R.drawable.squirtle,"Squirtle","Squiiiiirtle",33.0,19.368864,-99.157223))
    }

    open inner class MyPersonalLocationListener : LocationListener {
        constructor(){
            Util.log("si entra a location listener")
            location= Location("Start")
            location!!.latitude=0.0
            location!!.longitude=0.0
        }

        override fun onLocationChanged(loc: Location?) {
            location = loc
            Util.log("location : ${location.toString()}")
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }

    }

    inner class myThread : Thread {
        constructor():super(){
            oldLocation = Location ("Start")
            oldLocation!!.latitude=0.0
            oldLocation!!.longitude=0.0
        }
        override fun run() {
            while (true){
                if (oldLocation!!.distanceTo(location)==0f){
                    continue
                }
                oldLocation = location
                try {
                    runOnUiThread {
                        mMap!!.clear()
                        val myLocation = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(MarkerOptions().position(myLocation).title("It´ me!")
                                .snippet("Mario!!!")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))

                        //show pokemons
                        var listSize = pokemonList.size-1
                        for(i in 0..listSize){
                            var newPokemon = pokemonList[i]
                            if (newPokemon.IsCatch==false){
                                val pokemonLocation = LatLng(newPokemon.location!!.latitude, newPokemon.location!!.longitude)
                                mMap!!.addMarker(MarkerOptions().position(pokemonLocation).title(newPokemon.name!!)
                                        .snippet(newPokemon.des!!)
                                        .icon(BitmapDescriptorFactory.fromResource(newPokemon.image!!)))
                                mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))
                            }
                        }

                    }
                    Thread.sleep(1000)
                }catch (e:Exception){

                }
            }
        }
    }
}
