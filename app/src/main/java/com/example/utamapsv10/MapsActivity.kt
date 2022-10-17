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
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.model.PolylineOptions

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

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        //createMarker()
        createPolyLines()
        //Tipo de mapa
        mMap.mapType = MAP_TYPE_HYBRID

        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)
        enableLocation()
    }

    //Funcion para crear lineas multiples
    private fun createPolyLines(){
        val polylineOptions = PolylineOptions()
            .add(LatLng(-18.49248073172801, -70.29746890068053))
            .add(LatLng(-18.492353545984436,-70.29739379882812))
            .add(LatLng(-18.492338283688866, -70.29731869697571))
            .add(LatLng(-18.492109349092097,-70.29716849327087))
            .add(LatLng(-18.49203303749186,-70.29731333255768))
            .add(LatLng(-18.491956725857634,-70.29731333255768))
            .add(LatLng(-18.491585342085784,-70.29793560504913))
            .add(LatLng(-18.49139201870786,-70.29781758785248))
            .add(LatLng(-18.4917735777968,-70.29719531536102))
            .add(LatLng(-18.491875326743642,-70.29726505279541))
            .add(LatLng(-18.491493767881323,-70.29789268970488))
            .add(LatLng(-18.49211443653091,-70.29717922210693))
            .add(LatLng(-18.491839714619125,-70.29699951410294))
            .add(LatLng(-18.492055930975507,-70.29667228460312))
            .add(LatLng(-18.491961813300975,-70.29659181833267))
            .add(LatLng(-18.491875326743642,-70.29672592878342))
            .add(LatLng(-18.491956725857634,-70.29681712388992))
            .add(LatLng(-18.491837170895664,-70.29699951410294))
            .add(LatLng(-18.490748453785805,-70.29628604650497))
            .add(LatLng(-18.491010458769154,-70.29589176177979))
            .add(LatLng(-18.490873096983176,-70.29580861330032))
            .add(LatLng(-18.490880728196405,-70.2960741519928))
            .add(LatLng(-18.49075099752544,-70.29628068208693))
            .add(LatLng(-18.49041522356795,-70.29606342315674))
            .add(LatLng(-18.49041522356795,-70.29594540596008))
            .add(LatLng(-18.49049662337565,-70.29578983783722))
            .add(LatLng(-18.490486448401796,-70.295569896698))
            .add(LatLng(-18.489909017645836,-70.29518634080887))
            .add(LatLng(-18.48973858616969,-70.29542237520218))
            .add(LatLng(-18.489489298033593,-70.29564499855042))
            .add(LatLng(-18.489835248818753,-70.29598295688629))
            .add(LatLng(-18.489853055090272,-70.29604464769363))
            .add(LatLng(-18.490267686317946,-70.2959856390953))
            .add(LatLng(-18.490399961099705,-70.29604732990265))
            .add(LatLng(-18.48982507380564,-70.29605805873871))
            .add(LatLng(-18.48943842285921,-70.29646575450897))
            .add(LatLng(-18.488929670284605,-70.29678225517273))
            .add(LatLng(-18.48864476818304,-70.29706120491028))
            .add(LatLng(-18.488761781603507,-70.29715240001678))
            .add(LatLng(-18.488573542583623,-70.29746890068053))
            .add(LatLng(-18.487957948669294,-70.29706120491028))
            .add(LatLng(-18.488451441487186,-70.29613852500916))
            .add(LatLng(-18.488563367495576,-70.29620289802551))
            .add(LatLng(-18.488751606526645,-70.29597759246826))
            .add(LatLng(-18.488461616581876,-70.29573619365692))
            .add(LatLng(-18.488380215807403,-70.29573082923889))
            .add(LatLng(-18.487784971468912,-70.29533922672272))
            .add(LatLng(-18.488039349644357,-70.29472231864929))
            .add(LatLng(-18.489092471272844,-70.29558062553406))
            .add(LatLng(-18.48877704421768,-70.2959668636322))
            .add(LatLng(-18.489412985266362,-70.29644966125488))
            .color(ContextCompat.getColor(this, R.color.ruta))


            val polyline = mMap.addPolyline(polylineOptions)
    }

    //Funcion para crear marcadores
    private fun createMarker(){
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