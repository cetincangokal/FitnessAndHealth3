package com.example.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {
    private EditText editTextMail,editTextPass;
    private String txtMail,txtPass;
    private AppCompatButton buttonLogın;

    private TextView txtSign;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    private void init(){
        editTextMail = (EditText) findViewById(R.id.main_editTextMail);
        editTextPass = (EditText) findViewById(R.id.main_editTextPass);
        buttonLogın = (AppCompatButton) findViewById(R.id.main_buttonLogın);

        txtSign = (TextView) findViewById(R.id.main_txtSign);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mAuth = FirebaseAuth.getInstance();

    }


    //Giriş işlemi

    public void Logın(View view){

        txtMail = editTextMail.getText().toString();
        txtPass = editTextPass.getText().toString();
        if (!TextUtils.isEmpty(txtMail) && (!TextUtils.isEmpty(txtPass))){
            mAuth.signInWithEmailAndPassword(txtMail, txtPass)
                    .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            mUser = mAuth.getCurrentUser();
                            Intent i = new Intent(MainActivity.this, trainingPage.class);
                            startActivity(i);

                        }
                    }).addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


        }else

            Toast.makeText(this, "Mail ve şifre boş bırakılamaz", Toast.LENGTH_LONG).show();

    }
    public void SignUp(View view){
        Intent i = new Intent(MainActivity.this, SignUpPage.class);

        startActivity(i);
    }

}