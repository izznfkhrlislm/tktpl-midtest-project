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

            if (title.equals("Phone Information")) {
                mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
            } else if (title.equals("CPU Information")) {
                mainActivityFt.replace(R.id.detailMenuFragment, CpuInformationFragment.newInstance(title, description));
            } else if (title.equals("Batery Information")) {
                mainActivityFt.replace(R.id.detailMenuFragment, BatteryInformationFragment.newInstance(title, description));
            } else if (title.equals("System Information")) {
                mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
            } else if (title.equals("Settings")) {
                mainActivityFt.replace(R.id.detailMenuFragment, PhoneInformationFragment.newInstance(title, description));
            } else if (title.equals("About")) {
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