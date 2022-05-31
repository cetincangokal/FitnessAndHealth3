package com.example.fitnessandhealth.Fragment;


import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessandhealth.R;
import com.example.fitnessandhealth.model.Kullanici;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class person extends Fragment {
    private View v;
    private EditText editName,editSurname,editheight, editweight, editage, edithip, editweist, editneck;
    private Button btnUpdate;
    private DocumentReference mRef;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mUser;
    private Kullanici user;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_person, container, false);
        editName = v.findViewById(R.id.person_fragment_name);
        editSurname = v.findViewById(R.id.person_fragment_surname);
        editheight = v.findViewById(R.id.person_fragment_height);
        editweight = v.findViewById(R.id.person_fragment_weight);
        editage = v.findViewById(R.id.person_fragment_age);
        edithip = v.findViewById(R.id.person_fragment_hip);
        editweist = v.findViewById(R.id.person_fragment_weist);
        editneck = v.findViewById(R.id.person_fragment_neck);
        btnUpdate = v.findViewById(R.id.person_fragment_Update);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();

        mRef = mFirestore.collection("Kullanıcılar").document(mUser.getUid());
        mRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Toast.makeText(v.getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }
                if (value != null && value.exists()){
                    user = value.toObject(Kullanici.class);
                    if (user != null){
                        editName.setText(user.getKullaniciname());
                        editSurname.setText(user.getKullanicisurname());
                        editheight.setText(user.getKullaniciheight());
                        editweight.setText(user.getKullaniciweight());
                        editage.setText(user.getKullaniciage());
                        editweist.setText(user.getKullaniciweist());
                        edithip.setText(user.getKullanicihip());
                        editneck.setText(user.getKullanicineck());
                    }
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updatePerson();
            }
        });




        return v;
    }
    public void updatePerson(){
        String updatedName = editName.getText().toString();
        String updatedSurname = editSurname.getText().toString();
        String updatedHeight = editheight.getText().toString();
        String updatedWeight =  editweight.getText().toString();
        String updatedAge = editage.getText().toString();
        String updatedWeist = editweist.getText().toString();
        String updatedHip =  edithip.getText().toString();
        String updatedNeck = editneck.getText().toString();
        Kullanici mKullanici = new Kullanici(updatedName, updatedSurname, mUser.getEmail(), updatedHeight, updatedWeight, updatedAge, mUser.getUid(), updatedWeist, updatedHip, updatedNeck, user.getKullanicimale(), user.getKullanicifemale());
        mFirestore.collection("Kullanıcılar").document(mUser.getUid())
                .set(mKullanici);

    }
}