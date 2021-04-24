package com.tech.twestudentside.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.places.model.PlaceFields;
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
    public static File compressActualFile;
    public static File compressedImage;
    public static File imageFilePath;
    LinearLayout LL_calender;
    LinearLayout LL_user_profile;
    private Bitmap bitmap;
    /* access modifiers changed from: private */
    public String[] code = {"Male", "Female"};
    String dob = "";
    EditText edt_about;
    private int[] flags = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
    String gender = "";
    private ImageView img_userProfile;
    boolean isProduct = false;
    /* access modifiers changed from: private */
    public int mDay;
    /* access modifiers changed from: private */
    public int mMonth;
    /* access modifiers changed from: private */
    public int mYear;
    private Uri resultUri;
    Spinner spinnergender;
    TextView txt_age;
    TextView txt_dob;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView((int) R.layout.activity_upload_profile_pic);
        this.LL_calender = (LinearLayout) findViewById(R.id.LL_calender);
        this.txt_dob = (TextView) findViewById(R.id.txt_dob);
        this.txt_age = (TextView) findViewById(R.id.txt_age);
        this.spinnergender = (Spinner) findViewById(R.id.spinnergender);
        this.edt_about = (EditText) findViewById(R.id.edt_about);
        this.LL_user_profile = (LinearLayout) findViewById(R.id.LL_user_profile);
        this.img_userProfile = (ImageView) findViewById(R.id.img_userProfile);
        this.spinnergender.setAdapter(new CountrySpinnerAdapter(this, this.flags, this.code));
        this.LL_calender.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int unused = UploadProfilePicActivity.this.mYear = c.get(1);
                int unused2 = UploadProfilePicActivity.this.mMonth = c.get(2);
                int unused3 = UploadProfilePicActivity.this.mDay = c.get(5);
                new DatePickerDialog(UploadProfilePicActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        view.setVisibility(View.VISIBLE);
                        UploadProfilePicActivity uploadProfilePicActivity = UploadProfilePicActivity.this;
                        uploadProfilePicActivity.dob = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        UploadProfilePicActivity.this.txt_dob.setText(UploadProfilePicActivity.this.dob);
                        String age = UploadProfilePicActivity.this.getAge(year, monthOfYear + 1, dayOfMonth);
                        TextView textView = UploadProfilePicActivity.this.txt_age;
                        textView.setText(age + " Year");
                    }
                }, UploadProfilePicActivity.this.mYear, UploadProfilePicActivity.this.mMonth, UploadProfilePicActivity.this.mDay).show();
            }
        });
        this.LL_user_profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Dexter.withActivity(UploadProfilePicActivity.this).withPermissions("android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").withListener(new MultiplePermissionsListener() {
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            UploadProfilePicActivity.this.startActivityForResult(CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).getIntent(UploadProfilePicActivity.this), 1);
                            return;
                        }
                        UploadProfilePicActivity.this.showSettingDialogue();
                    }

                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
            }
        });
        this.spinnergender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                UploadProfilePicActivity uploadProfilePicActivity = UploadProfilePicActivity.this;
                uploadProfilePicActivity.gender = uploadProfilePicActivity.code[pos];
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void showSettingDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Need Permissions");
        builder.setMessage((CharSequence) "This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton((CharSequence) "GOTO SETTINGS", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                UploadProfilePicActivity.this.openSetting();
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void openSetting() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), (String) null));
        startActivityForResult(intent, 101);
    }

    public void signupProfilePic(View view) {
        String about = this.edt_about.getText().toString();
        if (!this.isProduct) {
            Toast.makeText(this, "Please Enter Profile Image.",  Toast.LENGTH_LONG).show();
        } else if (this.dob.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Dob",  Toast.LENGTH_LONG).show();
        } else if (about.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter about",  Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, TutorSelectionActivity.class);
            intent.putExtra("DOB", this.dob);
            intent.putExtra("Gender", this.gender);
            intent.putExtra(PlaceFields.ABOUT, about);
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: private */
    public String getAge(int year, int month, int day) {
        Calendar dob2 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob2.set(year, month, day);
        int age = today.get(1) - dob2.get(1);
        if (today.get(6) < dob2.get(6)) {
            age--;
        }
        return new Integer(age).toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropImage.ActivityResult result = CropImage.getActivityResult(data);
        if (requestCode != 1) {
            return;
        }
        if (resultCode == -1) {
            this.resultUri = result.getUri();
            try {
                this.bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), this.resultUri);
                imageFilePath = FileUtil.from(this, this.resultUri);
                this.img_userProfile.setImageResource(R.drawable.check_one);
                this.isProduct = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
         try {
                compressedImage = new Compressor(this).setMaxWidth(640).setMaxHeight(480).setQuality(75).setCompressFormat(Bitmap.CompressFormat.WEBP).setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()).compressToFile(imageFilePath);
                Log.e("ActivityTag", "imageFilePAth: " + compressedImage);
            } catch (IOException e2) {
                e2.printStackTrace();
                Toast.makeText(this, e2.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == 204) {
            result.getError();
        }
    }
}
