package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.GPSTracker;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.model.Get_detailsModel;
import com.tech.twestudentside.model.RelatedModel;
import com.tech.twestudentside.model.RelatedModelData;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class TrandingActivity extends AppCompatActivity implements OnMapReadyCallback {
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String TutorDetails_Id = "";
    ArrayList<RelatedModelData> arrayList = new ArrayList<>();
    TextView distance;
    GPSTracker gpsTracker;
    int[] icons = {R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse};
    String[] iconsName = {"Villas,Township,Penthouse,Compound,Office,Plots"};
    ImageView iv_back;
    Double lat;
    String latitude = "";
    Double lon;
    String longitude = "";
    FusedLocationProviderClient mFusedLocationClient;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    RoundedImageView profile_img;
    RecyclerView recyclerView1;
    SliderView sliderView;
    TextView txt_Affilation;
    TextView txt_Certification;
    TextView txt_Education;
    TextView txt_Experience;
    TextView txt_Language;
    TextView txt_abouts;
    TextView txt_address;
    TextView txt_empty_tutor;
    TextView txt_gender;
    TextView txt_payment_per_hr;
    TextView txt_tutor_name;
    TextView txt_year_count;
    //private SliderAdapterExample adapter;
    private SessionManager sessionManager;

    public TrandingActivity() {
        Double valueOf = Double.valueOf(0.0d);
        this.lat = valueOf;
        this.lon = valueOf;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity_tranding);
        this.txt_empty_tutor = (TextView) findViewById(R.id.txt_empty_tutor);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.txt_Education = (TextView) findViewById(R.id.txt_Education);
        this.txt_Experience = (TextView) findViewById(R.id.txt_Experience);
        this.txt_Language = (TextView) findViewById(R.id.txt_Language);
        this.txt_Certification = (TextView) findViewById(R.id.txt_Certification);
        this.txt_Affilation = (TextView) findViewById(R.id.txt_Affilation);
        this.profile_img = (RoundedImageView) findViewById(R.id.profile_img);
        this.txt_tutor_name = (TextView) findViewById(R.id.txt_tutor_name);
        this.txt_year_count = (TextView) findViewById(R.id.txt_year_count);
        this.distance = (TextView) findViewById(R.id.distance);
        this.txt_gender = (TextView) findViewById(R.id.txt_gender);
        this.txt_payment_per_hr = (TextView) findViewById(R.id.txt_payment_per_hr);
        this.txt_address = (TextView) findViewById(R.id.txt_address);
        this.txt_abouts = (TextView) findViewById(R.id.txt_abouts);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TrandingActivity.this.onBackPressed();
            }
        });
        this.sessionManager = new SessionManager((Activity) this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_viewTranding2);
        this.recyclerView1 = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.recyclerView1.setItemAnimator(new DefaultItemAnimator());
     if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getHomeDetailsApi();
          //  getRelatedApi();
        } else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        GPSTracker gPSTracker = new GPSTracker(this);
        this.gpsTracker = gPSTracker;
        if (gPSTracker.canGetLocation()) {
            this.latitude = String.valueOf(this.gpsTracker.getLatitude());
            this.longitude = String.valueOf(this.gpsTracker.getLongitude());
        } else {
            this.gpsTracker.showSettingsAlert();
        }
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        this.mapFrag = supportMapFragment;
        supportMapFragment.getMapAsync(this);
    }

    public void MapOpenInit(View view) {
        startActivity(new Intent(this, MapActivity.class));
    }

    public void lessonInitTranding(View view) {
        startActivity(new Intent(this, ReserveSpotActivity.class));
    }

    public void call_Video(View view) {
        startActivity(new Intent(this, VideoCallingActivity.class));
    }

    public void attendance(View view) {
        startActivity(new Intent(this, AttendanceActivity.class));
    }

    public void PhotoGalleryInit(View view) {
        startActivity(new Intent(this, PortFoliyoActivity.class));
    }

    private void getHomeDetailsApi() {
       String UserId= Preference.get(this, Preference.KEY_Tutor_id);
        RetrofitClients.getInstance().getApi().get_student_details(UserId).enqueue(new Callback<Get_detailsModel>() {
            public void onResponse(Call<Get_detailsModel> call, Response<Get_detailsModel> response) {
                try {
                    Get_detailsModel finallyPr = response.body();
                    TrandingActivity.this.progressBar.setVisibility(View.VISIBLE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        String userName = finallyPr.getResult().getTutorDetails().getUsername();
                        String ProfileImage = finallyPr.getResult().getImage();
                        String Gender = finallyPr.getResult().getGender();
                        String Lat = finallyPr.getResult().getTutorDetails().getLat();
                        String lon = finallyPr.getResult().getTutorDetails().getLon();
                        String Dob = finallyPr.getResult().getDob();
                        String perHourPayment = finallyPr.getResult().getPerHourPayment();
                        String Educaation = finallyPr.getResult().getEducation();
                        String Language = finallyPr.getResult().getLanguage();
                        String Certification = finallyPr.getResult().getCertification();
                        String Affilation = finallyPr.getResult().getAffiliations();
                        String str = status;
                        String TeacherDistace = finallyPr.getResult().getTeachDistance();
                        TrandingActivity.this.distance.setText(TeacherDistace);
                        if (finallyPr.getResult().getAddressDetails() != null) {
                            String Address = finallyPr.getResult().getAddressDetails().getAddress();
                            String str2 = TeacherDistace;
                            String str3 = Lat;
                            TrandingActivity.this.lat = Double.valueOf(finallyPr.getResult().getAddressDetails().getLat());
                            TrandingActivity.this.lon = Double.valueOf(finallyPr.getResult().getAddressDetails().getLon());
                            TrandingActivity.this.txt_address.setText(Address);
                        } else {
                            String str4 = Lat;
                        }
                        if (ProfileImage != null) {
                            ((RequestBuilder) ((RequestBuilder) Glide.with((FragmentActivity) TrandingActivity.this).load(ProfileImage).circleCrop()).placeholder((int) R.drawable.home_banner3)).into((ImageView) TrandingActivity.this.profile_img);
                        }
                        TrandingActivity.this.txt_tutor_name.setText(userName);
                        TrandingActivity.this.txt_gender.setText(Gender);
                        TextView textView = TrandingActivity.this.txt_payment_per_hr;
                        textView.setText("$" + finallyPr.getResult().getTotalChargesIndividual());
                        TrandingActivity.this.txt_Education.setText(Educaation);
                        TrandingActivity.this.txt_Language.setText(Language);
                        TrandingActivity.this.txt_Certification.setText(Certification);
                        TrandingActivity.this.txt_Affilation.setText(Affilation);
                        TrandingActivity.this.txt_abouts.setText(finallyPr.getResult().getAbout().toString());
                        if (Dob == null || Dob.equalsIgnoreCase("")) {
                        } else {
                            String[] datrrr = Dob.split("-");
                            Get_detailsModel get_detailsModel = finallyPr;
                            String[] strArr = datrrr;
                            String CalcuAge = TrandingActivity.this.getAge(Integer.parseInt(datrrr[2]), Integer.parseInt(datrrr[1]), Integer.parseInt(datrrr[0]));
                            TextView textView2 = TrandingActivity.this.txt_year_count;
                            textView2.setText(CalcuAge + " Years");
                        }
                        Toast.makeText(TrandingActivity.this, "Success", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String str5 = status;
                    Toast.makeText(TrandingActivity.this, message, Toast.LENGTH_LONG).show();
                    TrandingActivity.this.progressBar.setVisibility(GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<Get_detailsModel> call, Throwable t) {
                TrandingActivity.this.progressBar.setVisibility(GONE);
            }
        });
    }
/*

    private void getRelatedApi() {
        RetrofitClients.getInstance().getApi().related_tutor(this.sessionManager.getUserID(), this.latitude, this.longitude).enqueue(new Callback<RelatedModel>() {
            public void onResponse(Call<RelatedModel> call, Response<RelatedModel> response) {
                try {
                    RelatedModel finallyPr = response.body();
                    TrandingActivity.this.progressBar.setVisibility(GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (!status.equalsIgnoreCase("1")) {
                        TrandingActivity.this.progressBar.setVisibility(GONE);
                        TrandingActivity.this.txt_empty_tutor.setVisibility(GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    TrandingActivity.this.txt_empty_tutor.setVisibility(GONE);
                }
            }

            public void onFailure(Call<RelatedModel> call, Throwable t) {
                TrandingActivity.this.progressBar.setVisibility(GONE);
                TrandingActivity.this.txt_empty_tutor.setVisibility(GONE);
            }
        });
    }
*/

    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        googleMap.setMapType(3);
        LatLng latLng = new LatLng(this.lat.doubleValue(), this.lon.doubleValue());
        this.mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("First Pit Stop").icon(BitmapDescriptorFactory.defaultMarker(0.0f)));
        this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
    }

    /* access modifiers changed from: private */
    public String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            age--;
        }
        return new Integer(age).toString();
    }
}
