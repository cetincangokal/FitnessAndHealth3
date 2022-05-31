package com.example.fitnessandhealth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessandhealth.model.Kullanici;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class healthActivity extends AppCompatActivity {
    public double ygOrani;
    public double endeks;
    public double kaloriIhtiyacı;
    public double yzyAlani,yzyAlani1;
    public double ygsizAgirlik;
    public double iKilo;
    public double belCevresi,belCevresi2;
    public double boyunCevresi,boyunCevresi2;
    public double boy,boy2;
    public double kalcaCevresi,kalcaCevresi2;
    public double kilo,kilo2;
    public int yas;



    private AppCompatButton hesapla;
    private EditText yagOrani;
    private EditText kitleEndeksi;
    private EditText gunlukKalori;
    private EditText yuzeyAlani;
    private EditText ygsizVücutAgirligi;
    private EditText idealKilo;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mUser;
    private Kullanici user;
    private DocumentReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
        hesapla = (AppCompatButton) findViewById(R.id.Hesapla);
        yagOrani = (EditText) findViewById(R.id.YgOrani1);
        kitleEndeksi = (EditText) findViewById(R.id.Vki1);
        gunlukKalori = (EditText) findViewById(R.id.GunluKalori1);
        yuzeyAlani = (EditText) findViewById(R.id.VctYzyAlani1);
        ygsizVücutAgirligi = (EditText) findViewById(R.id.Yva1);
        idealKilo = (EditText) findViewById(R.id.IdealKılo1);


        mRef = mFirestore.collection("Kullanıcılar").document(mUser.getUid());
        mRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(healthActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null && value.exists()){
                    user = value.toObject(Kullanici.class);
                    if (user != null){
                        boy = (Integer.parseInt(user.getKullaniciheight()));
                        boy2 = (double)boy;
                        belCevresi = (Integer.parseInt(user.getKullaniciweist()));
                        belCevresi2 = (double)belCevresi;
                        boyunCevresi = (Integer.parseInt(user.getKullanicineck()));
                        boyunCevresi2 = (double)boyunCevresi;
                        kilo = (Integer.parseInt(user.getKullaniciweight()));
                        kilo2 = (double)kilo;
                        kalcaCevresi = (Integer.parseInt(user.getKullanicihip()));
                        kalcaCevresi2 = (double)kalcaCevresi;
                        yas = (Integer.parseInt(user.getKullaniciage()));



                    }
                }

            }
        });

        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getKullanicifemale() == null){
                    double x2 = belCevresi2 - boyunCevresi2;
                    ygOrani = 495 / (1.0324 + -0.19077 * Math.log10(x2) + 0.15456 * Math.log10(boy2)) - 450;
                    double x3 = boy2/100;
                    endeks = kilo2 / (x3 * x3);
                    kaloriIhtiyacı = 66 + ( 13.7 * kilo2 ) + ( 5 * boy2 ) - ( 6.8 * yas );
                    yzyAlani1 = (boy2 * kilo2 / 3600);
                    yzyAlani  = Math.pow(yzyAlani1,0.5);
                    iKilo = 50 + (2.3 * ((boy2/2.54)-60));
                    ygsizAgirlik = (0.73 * boy2)-59.42;


                    String txtygOrani = String.format("%.2f",ygOrani);
                    yagOrani.setText(txtygOrani);
                    String txtKitleEndeksi = String.format("%.2f",endeks);
                    kitleEndeksi.setText(txtKitleEndeksi);
                    String txtgunlukKalori = String.format("%.2f",kaloriIhtiyacı);
                    gunlukKalori.setText(txtgunlukKalori);
                    String txtYuzeyAlani = String.format("%.2f",yzyAlani);
                    yuzeyAlani.setText(txtYuzeyAlani);
                    String txtyagsizVucut = String.format("%.2f",ygsizAgirlik);
                    ygsizVücutAgirligi.setText(txtyagsizVucut);
                    String txtidealKilo = String.format("%.2f",iKilo);
                    idealKilo.setText(txtidealKilo);

                }


                else if(user.getKullanicimale() == null){
                    double x1 = belCevresi2 + kalcaCevresi2 - boyunCevresi2;
                    ygOrani = 495 / (1.29579 + -0.35004 * Math.log10(x1) + 0.22100 * Math.log10(boy2)) - 450;
                    double x3 = boy2/100;
                    endeks = kilo2 / (x3 * x3);
                    kaloriIhtiyacı=665 + ( 9.6 * kilo2 ) + ( 1.8 * boy2 ) - ( 4.7 * yas );
                    yzyAlani1 = (boy2 * kilo2 / 3600);
                    yzyAlani  = Math.pow(yzyAlani1,0.5);
                    iKilo = 45.5 + (2.3 * ((boy2/2.54)- 60));
                    ygsizAgirlik = (0.65 * boy2)- 50.74;

                    String txtygOrani = String.format("%.2f",ygOrani);
                    yagOrani.setText(txtygOrani);
                    String txtKitleEndeksi = String.format("%.2f",endeks);
                    kitleEndeksi.setText(txtKitleEndeksi);
                    String txtgunlukKalori = String.format("%.2f",kaloriIhtiyacı);
                    gunlukKalori.setText(txtgunlukKalori);
                    String txtYuzeyAlani = String.format("%.2f",yzyAlani);
                    yuzeyAlani.setText(txtYuzeyAlani);
                    String txtyagsizVucut = String.format("%.2f",ygsizAgirlik);
                    ygsizVücutAgirligi.setText(txtyagsizVucut);
                    String txtidealKilo = String.format("%.2f",iKilo);
                    idealKilo.setText(txtidealKilo);




                }
            }
        });






    }
}