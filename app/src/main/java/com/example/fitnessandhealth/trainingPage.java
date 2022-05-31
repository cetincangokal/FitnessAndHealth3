package com.example.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fitnessandhealth.Fragment.Home;
import com.example.fitnessandhealth.Fragment.person;
import com.example.fitnessandhealth.Fragment.setting;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class trainingPage extends AppCompatActivity {
    private BottomNavigationView mBottomBar;
    private setting settingFragment;
    private person personFragment;
    private Home homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_page);
        mBottomBar = (BottomNavigationView)findViewById(R.id.main_activity_bottomNavigationView);
        settingFragment = new setting();
        personFragment = new person();
        homeFragment = new Home();

        setFragment(homeFragment);




            mBottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.bottombar_home:
                            setFragment(homeFragment);
                            return true;
                        case R.id.bottombar_setting:
                            setFragment(settingFragment);
                            return true;
                        case R.id.bottombar_person:
                            setFragment(personFragment);
                            return true;

                        default:
                            return false;

                    }
                }
            });

        }
    private void setFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_frameLayout, fragment);
        transaction.commit();
    }

}
