package com.example.fitnessandhealth.Fragment;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessandhealth.MainActivity;
import com.example.fitnessandhealth.R;
import com.example.fitnessandhealth.model.Kullanici;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;



public class setting extends Fragment {
    private View v;
    private EditText editMail;
    private EditText editPass;
    private  String txtMail;

    private Button updatePassword1,delete;
    private DocumentReference mRef;
    private FirebaseFirestore mFirestore;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private Kullanici user;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_setting, container, false);
        editMail = v.findViewById(R.id.setting_frag_mail);

        Button updatePassword1 = v.findViewById(R.id.updatePassword);
        Button delete = v.findViewById(R.id.delete);



        mAuth = FirebaseAuth.getInstance();
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
                        editMail.setText(user.getKullanicimail());

                    }
                }

            }
        });
        updatePassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePassword();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirestore.collection("Kullanıcılar").document(mUser.getUid())
                                .delete();


                delete();
                Intent i = new Intent(getActivity(),MainActivity.class);
                startActivity(i);
            }
        });



        return v;


    }
    private void updatePassword(){
        txtMail = editMail.getText().toString().trim();
        mAuth.sendPasswordResetEmail(txtMail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(v.getContext(), "Mailinizi kontrol edin",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(v.getContext(), "Error!...",Toast.LENGTH_SHORT).show();

            }
        });


    }



    private void delete(){
        FirebaseUser FireBaseUser;
        FirebaseAuth FireBaseAuth;
        FireBaseAuth = FirebaseAuth.getInstance();
        FireBaseUser = FireBaseAuth.getCurrentUser();

        FireBaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(v.getContext(), "Hesabınız Silinmiştir",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(v.getContext(), "error!...",Toast.LENGTH_SHORT).show();
            }
        });




    }
}
