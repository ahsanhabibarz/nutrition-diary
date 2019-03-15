package com.example.nutritiondiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class NewChildActivity extends AppCompatActivity {


    Toolbar toolbar;

    EditText name,age,weight,gweight,heightft,heightinch;

    TextView bmi,idealweight,calorieintake,instruction,fatper,calculate;

    RadioButton radioButton;

    RadioGroup radioGroup;

    String gender = null;

    ImageView childImage,save;

    private Uri mainImageURI = null;

    boolean calculated = false;

    StorageReference storageReference;

    FirebaseAuth firebaseAuth;

    FirebaseFirestore firebaseFirestore;

    UploadTask uploadTask;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_new_child);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);

        save = (ImageView)findViewById(R.id.saveChild);
        name = (EditText)findViewById(R.id.cname);
        age = (EditText)findViewById(R.id.cage);
        weight = (EditText)findViewById(R.id.cweight);
        gweight = (EditText)findViewById(R.id.goalweight);
        heightft = (EditText)findViewById(R.id.heightft);
        heightinch = (EditText)findViewById(R.id.heightinch);
        calculate = (TextView) findViewById(R.id.calculatetv);

        firebaseAuth = FirebaseAuth.getInstance();

        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseFirestore = FirebaseFirestore.getInstance();

        bmi = (TextView)findViewById(R.id.bmi);
        fatper = (TextView)findViewById(R.id.fat);
        idealweight = (TextView)findViewById(R.id.gweight);
        calorieintake = (TextView)findViewById(R.id.calorieintake);
        instruction = (TextView)findViewById(R.id.instruction);

        childImage = (ImageView)findViewById(R.id.fimage);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                radioButton = (RadioButton)findViewById(i);

                gender = radioButton.getText().toString();


            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!name.getText().toString().isEmpty()&&!age.getText().toString().isEmpty()&&!weight.getText().toString().isEmpty()
                        && !gweight.getText().toString().isEmpty() && !heightft.getText().toString().isEmpty()
                        && !heightinch.getText().toString().isEmpty() && gender != null) {


                    if (!(Double.valueOf(age.getText().toString()) < 2) && !(Double.valueOf(age.getText().toString()) > 12)) {


                        double bmic = getBmi(Double.valueOf(weight.getText().toString()), Double.valueOf(heightft.getText().toString()), Double.valueOf(heightinch.getText().toString()));

                        double bodyfat = getBodyFat(bmic, Double.valueOf(age.getText().toString()), gender);

                        double idealw = getIdealWeight(Double.valueOf(heightft.getText().toString()), Double.valueOf(heightinch.getText().toString()));

                        DecimalFormat df = new DecimalFormat("#.##");
                        bmic = Double.valueOf(df.format(bmic));

                        idealw = Double.valueOf(df.format(idealw));

                        bodyfat = Double.valueOf(df.format(bodyfat));

                        fatper.setText(String.valueOf(bodyfat));

                        bmi.setText(String.valueOf(bmic));

                        idealweight.setText(String.valueOf(idealw));


                        if (Double.valueOf(age.getText().toString()) >= 2 && Double.valueOf(age.getText().toString()) <= 4) {

                            calorieintake.setText("1000");

                            instruction.setText("Dont Think too much about calorie intake");

                        } else if (Double.valueOf(age.getText().toString()) > 4) {

                            if (gender.equals("Female") && Double.valueOf(age.getText().toString()) < 8) {

                                if(bmic<=15){

                                    calorieintake.setText("1400");

                                    instruction.setText("Underweight");

                                }else if(bmic>15 && bmic<=18){

                                    calorieintake.setText("1200");

                                    instruction.setText("Normal");

                                }else if(bmic>18 && bmic<=22){

                                    calorieintake.setText("1100");
                                    instruction.setText("Overwight");
                                }else if(bmic>22){

                                    calorieintake.setText("1000");
                                    instruction.setText("Obese");
                                }

                            } else if(gender.equals("Female") && Double.valueOf(age.getText().toString()) > 7) {

                                if(bmic<=15){

                                    calorieintake.setText("1800");

                                    instruction.setText("Underweight");

                                }else if(bmic>15 && bmic<=18){

                                    calorieintake.setText("1600");
                                    instruction.setText("Normal");
                                }else if(bmic>18 && bmic<=22){

                                    calorieintake.setText("1500");
                                    instruction.setText("Overwight");
                                }else if(bmic>22){

                                    calorieintake.setText("1400");
                                    instruction.setText("Obese");
                                }

                            } else if (gender.equals("Male") && Double.valueOf(age.getText().toString()) < 8) {

                                if(bmic<=15){

                                    calorieintake.setText("1600");

                                    instruction.setText("Underweight");

                                }else if(bmic>15 && bmic<=18){

                                    calorieintake.setText("1400");
                                    instruction.setText("Normal");
                                }else if(bmic>18 && bmic<=22){

                                    calorieintake.setText("1300");
                                    instruction.setText("Overwight");
                                }else if(bmic>22){

                                    calorieintake.setText("1200");
                                    instruction.setText("Obese");
                                }

                            } else if(gender.equals("Male") && Double.valueOf(age.getText().toString()) > 8) {

                                if(bmic<=15){

                                    calorieintake.setText("1900");

                                    instruction.setText("Underweight");

                                }else if(bmic>15 && bmic<=18){

                                    calorieintake.setText("1800");
                                    instruction.setText("Normal");
                                }else if(bmic>18 && bmic<=22){

                                    calorieintake.setText("1600");
                                    instruction.setText("Overwight");
                                }else if(bmic>22){

                                    calorieintake.setText("1500");
                                    instruction.setText("Obese");
                                }
                            }


                        }


                        calculated = true;


                    }else{

                        Toast.makeText(NewChildActivity.this, "This app is designed only for 2-12 years age range", Toast.LENGTH_SHORT).show();
                    }


                }else{

                    Toast.makeText(NewChildActivity.this, "Please Fill Up Everything", Toast.LENGTH_SHORT).show();
                }

            }
        });




        childImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(NewChildActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted

                    ActivityCompat.requestPermissions(NewChildActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                }else{


                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(NewChildActivity.this);
                }

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mainImageURI!=null && calculated ){


                    saveInFirestore();


                }else if(mainImageURI == null){

                    Toast.makeText(NewChildActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
                }

                else if(!calculated){

                    Toast.makeText(NewChildActivity.this, "Please Calculate First", Toast.LENGTH_SHORT).show();

                }

            }
        });




    }


    void saveInFirestore(){


        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setTitle("Upload");
        progressDialog.setMessage("Uploading ...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();


        final StorageReference image_path = storageReference.child("childs").child(firebaseAuth.getCurrentUser().getUid().toString())
                .child(name.getText().toString()).child(name.getText().toString()+".jpg");


        uploadTask = image_path.putFile(mainImageURI);


        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return image_path.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();

                    addtoFirestore(downloadUri);


                } else {
                    // Handle failures
                    // ...
                    Toast.makeText(NewChildActivity.this, "Failed to upload try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress = (100.0* taskSnapshot.getBytesTransferred())/ taskSnapshot.getTotalByteCount();

                progressDialog.setProgress((int)progress);

            }
        });


    }


    void addtoFirestore(Uri uri){

        Map<String,Object> post = new HashMap<>();
        post.put("name", name.getText().toString());
        post.put("age",Double.parseDouble(age.getText().toString()));
        post.put("weight",Double.parseDouble(weight.getText().toString()));
        post.put("goalweight",Double.parseDouble(gweight.getText().toString()));
        post.put("heightft", Double.parseDouble(heightft.getText().toString()));
        post.put("heightinch",Double.parseDouble(heightinch.getText().toString()));
        post.put("bmi", Double.parseDouble(bmi.getText().toString()));
        post.put("idealweight",Double.parseDouble(idealweight.getText().toString()));
        post.put("calories",Double.parseDouble(calorieintake.getText().toString()));
        post.put("fatpercentage", Double.valueOf(fatper.getText().toString()));
        post.put("imagepath", uri.toString());
        firebaseFirestore.collection("parents").document(firebaseAuth.getCurrentUser().getUid()).collection("Childs").document().set(post).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                progressDialog.dismiss();

                Toast.makeText(NewChildActivity.this, "Upload Completed", Toast.LENGTH_SHORT).show();

                NewChildActivity.super.onBackPressed();

            }
        });


    }


    double getBmi(double k, double x, double y){

        double h = (x*0.3048+y*0.0254);

        return k/(h*h);
    }

    double getIdealWeight(double x, double y){

        double h = (x*0.3048+y*0.0254);

        return 18*(h*h);
    }


    double getBodyFat(double b, double a, String s){

        if(s.equals("Male")){


            return (1.51* b) - (0.70*a) - (3.6 * 1) + 1.4;

        }


        return (1.51* b) - (0.70*a) - (3.6 * 0) + 1.4;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI = result.getUri();
                childImage.setImageURI(mainImageURI);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }
    }
}
