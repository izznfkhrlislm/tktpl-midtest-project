package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters.SelectDeviceTabAdapter;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.SelectDeviceFetchApiFragment;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments.SelectDeviceFetchLocalFragment;

public class SelectDeviceActivity extends AppCompatActivity{
    private static final String TAG = "SelectDeviceActivity";

    private TabLayout tabLayout;
    private SelectDeviceTabAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        getSupportActionBar().setTitle("Select Device");

        adapter = new SelectDeviceTabAdapter(getSupportFragmentManager());
        adapter.addFragment(new SelectDeviceFetchApiFragment(), "Fetch From API");
        adapter.addFragment(new SelectDeviceFetchLocalFragment(), "Saved Device");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
