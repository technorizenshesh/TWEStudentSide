package com.tech.twestudentside.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.CountrySpinnerAdapter;
import com.tech.twestudentside.utils.FileUtil;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import id.zelory.compressor.Compressor;

public class UploadProfilePicActivity extends AppCompatActivity {

    LinearLayout LL_calender;
    LinearLayout LL_user_profile;
    private int mYear, mMonth,mDay;
    String dob ="";
    TextView txt_dob;
    TextView txt_age;
    EditText edt_about;
    Spinner spinnergender;
    private String code[] ={"Male","Female"};
    private int flags[]= {R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo};

    private Bitmap bitmap;
    private Uri resultUri;
    public static File imageFilePath, compressedImage, compressActualFile;
    private ImageView img_userProfile;
    boolean isProduct=false;
    String gender="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.activity_upload_profile_pic);

        LL_calender = findViewById(R.id.LL_calender);
        txt_dob = findViewById(R.id.txt_dob);
        txt_age = findViewById(R.id.txt_age);
        spinnergender = findViewById(R.id.spinnergender);
        edt_about = findViewById(R.id.edt_about);
        LL_user_profile = findViewById(R.id.LL_user_profile);
        img_userProfile = findViewById(R.id.img_userProfile);


        CountrySpinnerAdapter customAdapter=new CountrySpinnerAdapter(UploadProfilePicActivity.this,flags,code);
        spinnergender.setAdapter(customAdapter);

        LL_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadProfilePicActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                view.setVisibility(View.VISIBLE);
                                dob = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                                txt_dob.setText(dob);

                                String age = getAge(year,(monthOfYear+1),dayOfMonth);

                                txt_age.setText(age+" Year");

                            }
                        }, mYear, mMonth, mDay);

                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

                datePickerDialog.show();

            }
        });

        LL_user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dexter.withActivity(UploadProfilePicActivity.this)
                        .withPermissions(Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new MultiplePermissionsListener() {
                            @Override
                            public void onPermissionsChecked(MultiplePermissionsReport report) {
                                if (report.areAllPermissionsGranted()) {
                                    Intent intent = CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).getIntent(UploadProfilePicActivity.this);
                                    startActivityForResult(intent, 1);
                                } else {
                                    showSettingDialogue();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                gender  =code[pos];

            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showSettingDialogue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadProfilePicActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                openSetting();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    private void openSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    public void signupProfilePic(View view) {

        String about=edt_about.getText().toString();

        if(!isProduct)
        {
            Toast.makeText(this, "Please Enter Profile Image.", Toast.LENGTH_SHORT).show();

        }else if(dob.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Dob", Toast.LENGTH_SHORT).show();

        }else  if(about.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter about", Toast.LENGTH_SHORT).show();
        }else
        {

            Intent intent= new Intent(UploadProfilePicActivity.this, TutorSelectionActivity.class);
            intent.putExtra("DOB",dob);
            intent.putExtra("Gender",gender);
            intent.putExtra("about",about);
            startActivity(intent);
            finish();
        }
    }

    private String getAge(int year, int month, int day){

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    // Glide.with(this).load(bitmap).circleCrop().into(img_userProfile);
                    imageFilePath = FileUtil.from(this, resultUri);
                    img_userProfile.setImageResource(R.drawable.check_one);
                    isProduct = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    compressedImage = new Compressor(this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_PICTURES).getAbsolutePath())
                            .compressToFile(imageFilePath);
                    Log.e("ActivityTag", "imageFilePAth: " + compressedImage);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


}