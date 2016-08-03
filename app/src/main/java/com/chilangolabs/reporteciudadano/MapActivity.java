package com.chilangolabs.reporteciudadano;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mapbox.mapboxsdk.MapboxAccountManager;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.sergiocasero.revealfab.RevealFAB;

public class MapActivity extends AppCompatActivity {

    MapView mapView;
    Toolbar toolbar;
    RevealFAB revealfab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapboxAccountManager.start(this, getString(R.string.mapbox_token));
        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.mapview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        revealfab = (RevealFAB) findViewById(R.id.reveal_fab);

        mapView.onCreate(savedInstanceState);

        toolbar.setTitle("Reportes cercanos");
        setSupportActionBar(toolbar);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                MarkerViewOptions markerViewOptions = new MarkerViewOptions()
                        .position(new LatLng(19.431348, -99.136646));
                mapboxMap.addMarker(markerViewOptions);

                IconFactory iconFactory = IconFactory.getInstance(MapActivity.this);
                Drawable iconDrawable = ContextCompat.getDrawable(MapActivity.this, R.drawable.map_marker_radius_blue);
                Icon iconblue = iconFactory.fromDrawable(iconDrawable);

                Drawable iconDrawablegreen = ContextCompat.getDrawable(MapActivity.this, R.drawable.map_marker_radius_green);
                Icon icongreen = iconFactory.fromDrawable(iconDrawablegreen);

                Drawable iconDrawablegray = ContextCompat.getDrawable(MapActivity.this, R.drawable.map_marker_radius_gray);
                Icon icongray = iconFactory.fromDrawable(iconDrawablegray);

                Drawable iconDrawableyellow = ContextCompat.getDrawable(MapActivity.this, R.drawable.map_marker_radius_yellow);
                Icon iconyellow = iconFactory.fromDrawable(iconDrawableyellow);

                MarkerViewOptions markerViewOptions1 = new MarkerViewOptions()
                        .position(new LatLng(19.435348, -99.137146))
                        .title("Fuga de Agua")
                        .icon(iconblue);

                MarkerViewOptions markerViewOptions2 = new MarkerViewOptions()
                        .position(new LatLng(19.432308, -99.136899))
                        .title("Fuga de Agua")
                        .icon(iconblue);

                MarkerViewOptions markerViewOptions3 = new MarkerViewOptions()
                        .position(new LatLng(19.430987, -99.138722))
                        .title("Reporte de Bache")
                        .icon(icongray);

                MarkerViewOptions markerViewOptions4 = new MarkerViewOptions()
                        .position(new LatLng(19.429216, -99.136930))
                        .title("Problema Alumbrado Publico")
                        .icon(iconyellow);

                MarkerViewOptions markerViewOptions5 = new MarkerViewOptions()
                        .position(new LatLng(19.429459, -99.134945))
                        .title("Reporte de Basura")
                        .icon(icongreen);

                MarkerViewOptions markerViewOptions6 = new MarkerViewOptions()
                        .position(new LatLng(19.428568, -99.139086))
                        .title("Problema Alumbrado Publico")
                        .icon(iconyellow);

                MarkerViewOptions markerViewOptions7 = new MarkerViewOptions()
                        .position(new LatLng(19.430319, -99.132477))
                        .title("Reporte de Basura")
                        .icon(icongreen);

                MarkerViewOptions markerViewOptions8 = new MarkerViewOptions()
                        .position(new LatLng(19.430127, -99.133389))
                        .title("Fuga de Agua")
                        .icon(iconblue);

                MarkerViewOptions markerViewOptions9 = new MarkerViewOptions()
                        .position(new LatLng(19.430491, -99.135911))
                        .title("Reporte de Basura")
                        .icon(icongreen);

                MarkerViewOptions markerViewOptions10 = new MarkerViewOptions()
                        .position(new LatLng(19.431351, -99.135878))
                        .title("Reporte de Bache")
                        .icon(icongray);

                mapboxMap.addMarker(markerViewOptions1);
                mapboxMap.addMarker(markerViewOptions2);
                mapboxMap.addMarker(markerViewOptions3);
                mapboxMap.addMarker(markerViewOptions4);
                mapboxMap.addMarker(markerViewOptions5);
                mapboxMap.addMarker(markerViewOptions6);
                mapboxMap.addMarker(markerViewOptions7);
                mapboxMap.addMarker(markerViewOptions8);
                mapboxMap.addMarker(markerViewOptions9);
                mapboxMap.addMarker(markerViewOptions10);

                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        startActivity(new Intent(MapActivity.this, ReportActivity.class));
                        return true;
                    }
                });
            }
        });

        revealfab.setIntent(new Intent(MapActivity.this, ProfileActivity.class));

        revealfab.setOnClickListener(new RevealFAB.OnClickListener() {
            @Override
            public void onClick(RevealFAB button, View v) {
                button.startActivityWithAnimation();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
        revealfab.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }
}
