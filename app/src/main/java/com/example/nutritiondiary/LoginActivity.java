package com.example.nutritiondiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneno;
    private EditText code;
    private ImageView sendcode,verifycode;
    TextView timer;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private FirebaseAuth mAuth;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneno = (EditText)findViewById(R.id.editText);
        code = (EditText)findViewById(R.id.editText2);
        sendcode = (ImageView) findViewById(R.id.go);
        verifycode = (ImageView) findViewById(R.id.verify);
        mAuth = FirebaseAuth.getInstance();
        timer = (TextView)findViewById(R.id.timer);

        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerCode();
            }
        });


        verifycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifycode();
            }
        });
    }


    private void verifycode (){


        if(!code.getText().toString().isEmpty()){

            Glide.with(getApplicationContext()).load(R.drawable.loading_sp).into(verifycode);
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code.getText().toString());
            signInWithPhoneAuthCredential(credential);

        }else {

            Toast.makeText(this, "Please enter verification code", Toast.LENGTH_SHORT).show();
        }



    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            FirebaseUser user = task.getResult().getUser();
                            // ...
                        } else {
                            // Sign in failed, display a message and update th
                            Toast.makeText(LoginActivity.this, "Failed To Login", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void sendVerCode () {

        if (!phoneno.getText().toString().isEmpty()) {

        sendcode.setEnabled(false);

        Glide.with(getApplicationContext()).load(R.drawable.loading_sp).into(sendcode);

        String t = "+88" + phoneno.getText().toString();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                t,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                LoginActivity.this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        sendcode.setImageResource(R.drawable.pverified);
                        timer.setVisibility(View.VISIBLE);
                        code.setVisibility(View.VISIBLE);
                        verifycode.setVisibility(View.VISIBLE);


                        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long l) {

                                timer.setText(String.valueOf(l / 1000) + "s");
                            }

                            @Override
                            public void onFinish() {

                                sendcode.setImageResource(R.drawable.go);

                                sendcode.setEnabled(true);

                                timer.setVisibility(View.GONE);
                                sendcode.setVisibility(View.VISIBLE);

                                code.setVisibility(View.GONE);
                                verifycode.setVisibility(View.GONE);

                            }
                        }.start();

                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        Toast.makeText(LoginActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();

                        sendcode.setEnabled(true);
                    }

                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);

                        codeSent = s;
                    }

                });        // OnVerificationStateChangedCallbacks
        }else{

            Toast.makeText(LoginActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
        }

    }

}
