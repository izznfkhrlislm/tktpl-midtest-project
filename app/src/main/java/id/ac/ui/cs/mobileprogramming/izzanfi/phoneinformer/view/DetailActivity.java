package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.AboutFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.BatteryInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.CpuInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.PhoneInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.SettingsFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.SystemInformationFragment;

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

        if (title.equals("Phone Information")) {
            detailActivityFt.replace(R.id.detailFragment, PhoneInformationFragment.newInstance(title, description));
        } else if (title.equals("CPU Information")) {
            detailActivityFt.replace(R.id.detailFragment, CpuInformationFragment.newInstance(title, description));
        } else if (title.equals("Battery Information")) {
            detailActivityFt.replace(R.id.detailFragment, BatteryInformationFragment.newInstance(title, description));
        } else if (title.equals("System Information")) {
            detailActivityFt.replace(R.id.detailFragment, SystemInformationFragment.newInstance(title, description));
        } else if (title.equals("Settings")) {
            detailActivityFt.replace(R.id.detailFragment, SettingsFragment.newInstance(title, description));
        } else if (title.equals("About")) {
            detailActivityFt.replace(R.id.detailFragment, AboutFragment.newInstance(title, description));
        }

        detailActivityFt.commit();
    }
}
