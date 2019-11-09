package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters.Callable;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters.DeviceSpecificationAdapter;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.DeviceSpecificationViewModel;

public class SelectDeviceActivity extends AppCompatActivity implements Callable {
    private static final String TAG = "SelectDeviceActivity";
    List<DeviceSpecification> deviceSpecificationList = new ArrayList<>();
    DeviceSpecificationAdapter dataAdapter;
    ContentLoadingProgressBar progressBar;
    DeviceSpecificationViewModel viewModel;
    RecyclerView rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_device);
        getSupportActionBar().setTitle("Select Device");
        rv = findViewById(R.id.selectDeviceActivityRv);
        progressBar = findViewById(R.id.spinnerLoading);

        progressBar.setVisibility(View.VISIBLE);
        viewModel = ViewModelProviders.of(this).get(DeviceSpecificationViewModel.class);
        viewModel.init(getApplication(), Build.MANUFACTURER, Build.MODEL);
        progressBar.setVisibility(View.GONE);
        viewModel.getMutableLiveData().observe(this, new Observer<List<DeviceSpecification>>() {
            @Override
            public void onChanged(@Nullable  List<DeviceSpecification> deviceSpecifications) {
                System.out.println("called!");
                if (deviceSpecifications != null) {
                    System.out.println(deviceSpecifications.toString());
                    deviceSpecificationList.addAll(deviceSpecifications);
                }
                System.out.println("set!");
                dataAdapter.notifyDataSetChanged();
            }
        });

        setupRv();
    }

    private void setupRv() {
        if (dataAdapter == null) {
            dataAdapter = new DeviceSpecificationAdapter(SelectDeviceActivity.this, deviceSpecificationList, this);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(dataAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
        } else {
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickCallback(DeviceSpecification deviceSpecification) {
        Toast.makeText(this, deviceSpecification.getDeviceName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();

    }
}
