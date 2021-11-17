package com.example.picked;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

import timber.log.Timber;

public class LocationSearchCurrentActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    //source: https://github.com/android/permissions-samples/blob/main/RuntimePermissionsBasic/Application/src/main/java/com/example/android/basicpermissions/MainActivity.java

    private static final int PERMISSION_REQUEST_LOCATION = 0;
    private View mLayout;
    private FusedLocationProviderClient fusedLocationClient;
    private Location foundLocation;
    private Geocoder geocoder;
    private List<Address> searchAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mLayout = findViewById(R.id.location_layout);

        // Register a listener for the 'get location' button.
        findViewById(R.id.find_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetLocation();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            // Request for location permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start location preview Activity.
                Snackbar.make(mLayout, R.string.location_permission_granted,
                        Snackbar.LENGTH_SHORT)
                        .show();
                requestLocationPermission();
            } else {
                // Permission request was denied.
                Snackbar.make(mLayout, R.string.location_permission_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    private void GetLocation() {
        // BEGIN_INCLUDE(startLocation)
        // Check if the location permission has been granted
        Timber.d("STARTING GET LOCATION RN");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start location preview
            Snackbar.make(mLayout,
                    R.string.location_permission_available,
                    Snackbar.LENGTH_SHORT).show();

            // source: https://stackoverflow.com/a/50448772
            LocationRequest mLocationRequest = LocationRequest.create();
            mLocationRequest.setInterval(60000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationCallback mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            geocoder = new Geocoder(LocationSearchCurrentActivity.this, Locale.getDefault());

                            foundLocation = location;
                            try {
                                searchAddresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                Activity activity = LocationSearchCurrentActivity.this;
                                SharedPreferences locationPref = activity.getSharedPreferences("location", activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = locationPref.edit();
                                String address = searchAddresses.get(0).getLocality() + "," + searchAddresses.get(0).getAdminArea() + "," + searchAddresses.get(0).getPostalCode();
                                editor.putString(getString(R.string.found_location), address);
                                editor.apply();
                                Timber.d("HERE IS YOUR ADDRESS" + String.valueOf(searchAddresses.get(0)));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, mLocationCallback, null);

            //startCreateNewPlantEntry();
        } else {
            // Permission is missing and must be requested.
            requestLocationPermission();
        }
    }

    /**
     * Requests the {@link android.Manifest.permission#ACCESS_FINE_LOCATION} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestLocationPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(mLayout, R.string.location_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(LocationSearchCurrentActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSION_REQUEST_LOCATION);
                }
            }).show();

        } else {
            Snackbar.make(mLayout, R.string.location_unavailable, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_LOCATION);
        }
    }

    private void startCreateNewPlantEntry() {
        Intent intent = new Intent(this, CreateNewPlantEntryActivity.class);
        startActivity(intent);
    }
}