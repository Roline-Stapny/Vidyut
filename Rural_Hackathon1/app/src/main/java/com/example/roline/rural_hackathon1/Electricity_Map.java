package com.example.roline.rural_hackathon1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import static com.example.roline.rural_hackathon1.R.id.map;

public class Electricity_Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //lat long on india
    double BelmanLat=13.1664,BelmanLong=74.8712;
    double ShirvaLat=13.2175 ,ShirvaLong=74.8243;
    double NitteLat=13.1818,NitteLong=74.9413;

    double delhiLat=28.7041,delhiLong= 77.1025,mpLat=22.9734,mpLong=78.6569,
            ChenLat=13.0827,CheLnog=80.2707,apLat=27.845148,apLong=95.247345,
            hpLat=31.1048,hpLong=77.1734,upLat=26.8467,upLong=80.9462;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity__map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
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



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
      /*  LatLng Shirva = new LatLng(ShirvaLat, ShirvaLong);
       mMap.addMarker(new MarkerOptions().position(Shirva).title("Marker in Shirva"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(karnataka,14.0f));



        // Add a marker in Sydney and move the camera
        LatLng Belman = new LatLng(BelmanLat, BelmanLong);
        mMap.addMarker(new MarkerOptions().position(Belman).title("Marker in Belman"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(karnataka,14.0f));*/



        // Add a marker in Sydney and move the camera
       /* LatLng Nitte = new LatLng(NitteLat, NitteLong);
        mMap.addMarker(new MarkerOptions().position(Nitte).title("Marker in Nitte"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(karnataka,14.0f));*/


        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(mpLat, mpLong) , 5.0f) );

      /*  mMap.addCircle(new CircleOptions()
                .center(new LatLng(ShirvaLat,ShirvaLong))
                .radius(1000)
                .fillColor(Color.RED));

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(BelmanLat,BelmanLong))
                .radius(1000)
                .fillColor(Color.RED));*/

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(NitteLat,NitteLong))
                .radius(50000)
                .fillColor(Color.RED));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(delhiLat,delhiLong))
                .radius(50000)
                .fillColor(Color.YELLOW));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(mpLat,mpLong))
                .radius(50000)
                .fillColor(Color.YELLOW));

        mMap.addCircle(new CircleOptions()
                .center(new LatLng(ChenLat,CheLnog))
                .radius(50000)
                .fillColor(Color.YELLOW));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(apLat,apLong))
                .radius(50000)
                .fillColor(Color.RED));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(hpLat,hpLong))
                .radius(50000)
                .fillColor(Color.RED));
        mMap.addCircle(new CircleOptions()
                .center(new LatLng(upLat,upLong))
                .radius(50000)
                .fillColor(Color.RED));




    }
}
