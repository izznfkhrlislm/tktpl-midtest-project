package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.DeviceSpecificationViewModel;

public class SelectDeviceFetchLocalFragment extends Fragment implements Callable {
    List<DeviceSpecification> deviceSpecificationList = new ArrayList<>();
    DeviceSpecificationAdapter dataAdapter;
    DeviceSpecificationViewModel viewModel;
    RecyclerView rv;
    View loadingAnimation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_device_specification_fetch_from_api, container, false);

        rv = view.findViewById(R.id.selectDeviceActivityRv);
        loadingAnimation = view.findViewById(R.id.loadingView);

        viewModel = ViewModelProviders.of(this).get(DeviceSpecificationViewModel.class);
        loadingAnimation.setVisibility(View.VISIBLE);
        viewModel.init(getActivity().getApplication(), Build.MANUFACTURER, Build.MODEL);
        viewModel.getAllDeviceSpecifications().observe(this, new Observer<List<DeviceSpecification>>() {
            @Override
            public void onChanged(@Nullable  List<DeviceSpecification> deviceSpecifications) {
                loadingAnimation.setVisibility(View.GONE);
                if (deviceSpecifications != null) {
                    deviceSpecificationList.addAll(deviceSpecifications);
                }
                dataAdapter.notifyDataSetChanged();
            }
        });
        setupRv();

        return view;
    }

    private void setupRv() {
        if (dataAdapter == null) {
            dataAdapter = new DeviceSpecificationAdapter(getContext(), deviceSpecificationList, this);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(dataAdapter);
            rv.setItemAnimator(new DefaultItemAnimator());
        } else {
            dataAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClickCallback(DeviceSpecification deviceSpecification) {
        Toast.makeText(getContext(), deviceSpecification.getDeviceName() + " selected!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.putExtra("selectedObj", deviceSpecification);
        getActivity().setResult(98, intent);
        getActivity().finish();
    }
}
