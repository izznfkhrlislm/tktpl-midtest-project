package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.View;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.BatteryInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.CpuInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.MenuFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.PhoneInformationFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.fragments.SystemInformationFragment;

public class MainActivity extends AppCompatActivity implements Communicable {
    private static final String TAG = "MainActivity";
    private boolean mIsDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsDualPane = false;
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        FragmentManager mainActivityFm = getSupportFragmentManager();
        FragmentTransaction mainActivityFt = mainActivityFm.beginTransaction();
        mainActivityFt.replace(R.id.menuFragment, new MenuFragment());
        mainActivityFt.commit();

        View fragmentDetailView = findViewById(R.id.detailMenuFragment);
        if (fragmentDetailView != null) {
            mIsDualPane = true;
        }
    }

    @Override
    public void displayDetails(String title, String description) {
        Log.d(TAG, "title: " + title);
        Log.d(TAG, "mIsDualPane: " + mIsDualPane);
        if (mIsDualPane) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment instanceof MenuFragment) {
                    continue;
                } else {
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }
            }

            //TODO: Implement dual pane view (for landscape)
            FragmentManager mainActivityFm = getSupportFragmentManager();
            FragmentTransaction mainActivityFt = mainActivityFm.beginTransaction();

            switch (title) {
                case "Phone Information":
                    mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
                case "CPU Information":
                    mainActivityFt.replace(R.id.detailMenuFragment, CpuInformationFragment.newInstance(title, description));
                case "Battery Information":
                    mainActivityFt.replace(R.id.detailMenuFragment, BatteryInformationFragment.newInstance(title, description));
                case "System Information":
                    mainActivityFt.replace(R.id.detailMenuFragment, SystemInformationFragment.newInstance(title, description));
                case "Settings":
                    mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
                case "About":
                    mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
            }

            mainActivityFt.commit();

        } else {
            //TODO: Implement single pane view (for portrait)
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("desc", description);
            startActivity(intent);
        }
    }

}