package com.example.utamapsv10

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.utamapsv10.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    companion object{
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        createMarker()
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
        enableLocation()
    }

    //Funcion para crear marcadores
    private fun createMarker(){
        /*
        potoooooooooo
         */
        val universidadTarapaca = LatLng(-18.487886, -70.294793)
        val marker = MarkerOptions().position(universidadTarapaca).title("Esta es la entrada principal de la UTA")
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(universidadTarapaca, 18F), 3000, null)
    }

    //Funcion para saber si el permiso esta aceptado
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    //Funcion para comprobar si otorgo el permiso, entregar la ubicacion y si no pedirle al usuario que la acepte
    private fun enableLocation(){
        if (!::mMap.isInitialized) return
        if (isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = true
        }
        else{
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(this, "Ve a ajustes y acepta los permisos0", Toast.LENGTH_SHORT).show()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE_LOCATION -> if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mMap.isMyLocationEnabled = true
            }
            else{
                Toast.makeText(this, "Para activar la localizacion ve a ajuste y acepta los permisos", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mMap.isInitialized) return
        if (!isLocationPermissionGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la localizacion ve a ajuste y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Estas en ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()
    }
}