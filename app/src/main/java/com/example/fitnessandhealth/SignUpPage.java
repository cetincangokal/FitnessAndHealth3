package com.example.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessandhealth.model.Kullanici;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;



public class SignUpPage extends AppCompatActivity {
    private EditText name, surname, mail, password, height, weight, age, hip, weist, neck;
    private CheckBox male, female;
    private String txtname, txtsurname, txtmail, txtpassword, txtheight, txtweight, txtage, txthip, txtweist, txtneck, txtmale, txtfemale;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore mFirestore;
    private DatabaseReference mReferance;
    private Kullanici mKullanici;

    private void init() {
        name = (EditText) findViewById(R.id.sign_up_page_name);
        surname = (EditText) findViewById(R.id.sign_up_page_surname);
        mail = (EditText) findViewById(R.id.sign_up_page_mail);
        password = (EditText) findViewById(R.id.sign_up_page_password);
        height = (EditText) findViewById(R.id.sign_up_page_height);
        weight = (EditText) findViewById(R.id.sign_up_page_weight);
        age = (EditText) findViewById(R.id.sign_up_page_age);
        hip = (EditText) findViewById(R.id.sign_up_page_hip);
        weist = (EditText) findViewById(R.id.sign_up_page_weist);
        neck = (EditText) findViewById(R.id.sign_up_page_neck);
        male = (CheckBox) findViewById(R.id.sign_up_page_checkbox_male);
        female = (CheckBox) findViewById(R.id.sign_up_page_checkbox_female);
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mReferance = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        init();



    }
    // girilen değerleri stringe çevirme

    public void SıgnUp(View view) {
        txtname = name.getText().toString();
        txtsurname = surname.getText().toString();
        txtmail = mail.getText().toString();
        txtpassword = password.getText().toString();
        txtheight = height.getText().toString();
        txtweight = weight.getText().toString();
        txtage = age.getText().toString();
        txtweist = weist.getText().toString();
        txthip = hip.getText().toString();
        txtneck = neck.getText().toString();
        txtmale = male.getText().toString();
        txtfemale = female.getText().toString();


        //değer girilirken boş olma yada olmama durumu ve alınan verileri database e aktarma
        if (!TextUtils.isEmpty(txtname)) {
            if (!TextUtils.isEmpty(txtsurname)) {
                if (!TextUtils.isEmpty(txtmail) && (!TextUtils.isEmpty(txtpassword))) {


                    if (!TextUtils.isEmpty(txtheight)) {
                        if (!TextUtils.isEmpty(txtweight)) {
                            if (!TextUtils.isEmpty(txtage)) {
                                if (!TextUtils.isEmpty(txtweist)){
                                    if(!TextUtils.isEmpty(txthip)){
                                        if (!TextUtils.isEmpty(txtneck)) {
                                            if (male.isChecked()) {

                                                mAuth.createUserWithEmailAndPassword(txtmail, txtpassword)
                                                        .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                if (task.isSuccessful()) {
                                                                    mUser = mAuth.getCurrentUser();
                                                                    if (mUser != null) {
                                                                        mKullanici = new Kullanici(txtname, txtsurname, txtmail, txtheight, txtweight, txtage, mUser.getUid(), txtweist, txthip, txtneck, txtmale, null);
                                                                        mFirestore.collection("Kullanıcılar").document(mUser.getUid())
                                                                                .set(mKullanici)
                                                                                .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            showToa("Başarıyla Kayıt Oldunuz");
                                                                                            finish();
                                                                                            startActivity(new Intent(SignUpPage.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                                                                        } else
                                                                                            Toast.makeText(SignUpPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                            }
                                                        });

                                            } else if (female.isChecked()) {
                                                mAuth.createUserWithEmailAndPassword(txtmail, txtpassword)
                                                        .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                                if (task.isSuccessful()) {
                                                                    mUser = mAuth.getCurrentUser();
                                                                    if (mUser != null) {
                                                                        mKullanici = new Kullanici(txtname, txtsurname, txtmail, txtheight, txtweight, txtage, mUser.getUid(), txtweist, txthip, txtneck, null, txtfemale);
                                                                        mFirestore.collection("Kullanıcılar").document(mUser.getUid())
                                                                                .set(mKullanici)
                                                                                .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                        if (task.isSuccessful()) {
                                                                                            showToa("Başarıyla Kayıt Oldunuz");
                                                                                            finish();
                                                                                            startActivity(new Intent(SignUpPage.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                                                                        } else
                                                                                            Toast.makeText(SignUpPage.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                            }
                                                        });
                                            }else
                                                showToa("Cinsiyet boş bırakılamaz");






                                        }else
                                            showToa("Boyun çevresi boş bırakılamaz");
                                    }else
                                        showToa("Kalça çevresi boş bırakılamaz");
                                }else
                                    showToa("Bel çevresi boş bırakılamaz");
                            }else showToa("Yaş boş bırakılamaz");
                        }else showToa("Kilo boş bırakılamaz");
                    }else showToa("Boy boş bırakılamaz");
                } else
                    Toast.makeText(this, "Mail ve şifre boş bırakılamaz", Toast.LENGTH_LONG).show();
            }else showToa("Soyad boş bırakılamaz");
        }else showToa("İsim boş bırakılamaz");
    }

    private void showToa(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }
}