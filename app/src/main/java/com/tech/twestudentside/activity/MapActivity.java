package com.tech.twestudentside.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String City = "";
    String Community = "";
    String Country = "";
    LinearLayout LL_save;
    RelativeLayout RR_Next;
    String address = "";
    List<Address> addresses;
    double lat1 = 0.0d;
    LatLng latLng;
    Location location;
    double lon1 = 0.0d;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    GoogleMap mGoogleMap;
    Location mLastLocation;
    LocationCallback mLocationCallback = new LocationCallback() {
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                MapActivity.this.location = locationList.get(locationList.size() - 1);
                Log.i("MapsActivity", "Location: " + MapActivity.this.location.getLatitude() + " " + MapActivity.this.location.getLongitude());
                MapActivity mapActivity = MapActivity.this;
                mapActivity.mLastLocation = mapActivity.location;
                if (MapActivity.this.mCurrLocationMarker != null) {
                    MapActivity.this.mCurrLocationMarker.remove();
                }
                MapActivity mapActivity2 = MapActivity.this;
                mapActivity2.latLng = new LatLng(mapActivity2.location.getLatitude(), MapActivity.this.location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(MapActivity.this.latLng);
                markerOptions.title("Current Position");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(300.0f));
                MapActivity mapActivity3 = MapActivity.this;
                mapActivity3.mCurrLocationMarker = mapActivity3.mGoogleMap.addMarker(markerOptions);
                MapActivity.this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 11.0f));
                MapActivity.this.mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    public void onMapClick(LatLng point) {
                        MapActivity.this.latLng = new LatLng(MapActivity.this.location.getLatitude(), MapActivity.this.location.getLongitude());
                        MapActivity.this.lat1 = point.latitude;
                        MapActivity.this.lon1 = point.longitude;
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(MapActivity.this.latLng);
                        markerOptions.title("Current Position");
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(300.0f));
                        MapActivity.this.mCurrLocationMarker = MapActivity.this.mGoogleMap.addMarker(markerOptions);
                        MapActivity.this.mGoogleMap.clear();
                        MapActivity.this.mGoogleMap.addMarker(new MarkerOptions().position(point).title("First Pit Stop").icon(BitmapDescriptorFactory.defaultMarker(120.0f)));
                        MapActivity.this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapActivity.this.latLng, 11.0f));
                    }
                });
            }
        }
    };
    LocationRequest mLocationRequest;
    SupportMapFragment mapFrag;
    ProgressBar progressBar;
    SessionManager sessionManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_map);
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        this.mapFrag = supportMapFragment;
        supportMapFragment.getMapAsync(this);
        this.LL_save = (LinearLayout) findViewById(R.id.LL_save);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.sessionManager = new SessionManager((Activity) this);
        this.LL_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("latitude", "inside latitude--" + MapActivity.this.lat1 + "  lon latitude--" + MapActivity.this.lon1);
                Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                try {
                    Log.e("latitude", "inside latitude--" + MapActivity.this.lat1);
                    MapActivity.this.addresses = geocoder.getFromLocation(MapActivity.this.lat1, MapActivity.this.lon1, 1);
                    Log.e("addresses", "addresses latitude--" + MapActivity.this.addresses);
                    if (MapActivity.this.addresses != null && MapActivity.this.addresses.size() > 0) {
                        MapActivity.this.address = MapActivity.this.addresses.get(0).getAddressLine(0);
                        String locality = MapActivity.this.addresses.get(0).getLocality();
                        String adminArea = MapActivity.this.addresses.get(0).getAdminArea();
                        String countryName = MapActivity.this.addresses.get(0).getCountryName();
                        String postalCode = MapActivity.this.addresses.get(0).getPostalCode();
                        MapActivity.this.addresses.get(0).getFeatureName();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (MapActivity.this.sessionManager.isNetworkAvailable()) {
                    MapActivity.this.progressBar.setVisibility(0);
                    MapActivity.this.AddAddress();
                    return;
                }
                Toast.makeText(MapActivity.this, R.string.checkInternet, 0).show();
            }
        });
    }

    public void onPause() {
        super.onPause();
        FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
        if (fusedLocationProviderClient != null) {
            fusedLocationProviderClient.removeLocationUpdates(this.mLocationCallback);
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        googleMap.setMapType(4);
        LocationRequest locationRequest = new LocationRequest();
        this.mLocationRequest = locationRequest;
        locationRequest.setInterval(120000);
        this.mLocationRequest.setFastestInterval(120000);
        this.mLocationRequest.setPriority(102);
        if (Build.VERSION.SDK_INT < 23) {
            this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
            this.mGoogleMap.setMyLocationEnabled(true);
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
            this.mGoogleMap.setMyLocationEnabled(true);
        } else {
            checkLocationPermission();
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
            new AlertDialog.Builder(this).setTitle("Location Permission Needed").setMessage("This app needs the Location permission, please accept to use location functionality").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MapActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
                }
            }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 99) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Toast.makeText(this, "permission denied", 1).show();
            } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                this.mFusedLocationClient.requestLocationUpdates(this.mLocationRequest, this.mLocationCallback, Looper.myLooper());
                this.mGoogleMap.setMyLocationEnabled(true);
            }
        }
    }

    /* access modifiers changed from: private */
    public void AddAddress() {
        String UserId = this.sessionManager.getUserID();
        RetrofitClients.getInstance().getApi().add_address(UserId, "Home", this.address, String.valueOf(this.lat1), String.valueOf(this.lon1)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    JSONObject jSONObject = jsonObject.getJSONObject("result");
                    if (status.equalsIgnoreCase("1")) {
                        MapActivity.this.progressBar.setVisibility(8);
                        MapActivity.this.finish();
                        return;
                    }
                    Toast.makeText(MapActivity.this, message, 0).show();
                    MapActivity.this.progressBar.setVisibility(8);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                MapActivity.this.progressBar.setVisibility(8);
                Toast.makeText(MapActivity.this, t.getMessage(), 0).show();
            }
        });
    }
}
