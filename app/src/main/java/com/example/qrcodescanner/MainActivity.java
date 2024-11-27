package com.example.qrcodescanner;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import com.example.qrcodescanner.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_container, new ScanCodeFragment());
        trans.commit();

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction trans;
                if(tab.getPosition()==0)
                {
                    trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.main_container, new ScanCodeFragment());
                    trans.commit();

                }else{
                    trans = getSupportFragmentManager().beginTransaction();
                    trans.replace(R.id.main_container, new GenerateCodeFragment());
                    trans.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }

        });

    }
}