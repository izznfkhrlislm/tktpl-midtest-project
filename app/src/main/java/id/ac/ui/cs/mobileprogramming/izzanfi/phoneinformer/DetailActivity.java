package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.AboutFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.BatteryInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.CpuInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.PhoneInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.SettingsFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.SystemInformationFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detailActivityIntent = getIntent();
        String title = detailActivityIntent.getStringExtra("title");
        String description = detailActivityIntent.getStringExtra("desc");

        FragmentManager detailActivityFm = getSupportFragmentManager();
        FragmentTransaction detailActivityFt = detailActivityFm.beginTransaction();

        switch (title) {
            case "Phone Information":
                detailActivityFt.replace(R.id.detailFragment, PhoneInformationFragment.newInstance(title, description));
            case "CPU Information":
                detailActivityFt.replace(R.id.detailFragment, CpuInformationFragment.newInstance(title, description));
            case "Battery Information":
                detailActivityFt.replace(R.id.detailFragment, BatteryInformationFragment.newInstance(title, description));
            case "System Information":
                detailActivityFt.replace(R.id.detailFragment, SystemInformationFragment.newInstance(title, description));
            case "Settings":
                detailActivityFt.replace(R.id.detailFragment, SettingsFragment.newInstance(title, description));
            case "About":
                detailActivityFt.replace(R.id.detailFragment, AboutFragment.newInstance(title, description));
        }

        detailActivityFt.commit();
    }
}
