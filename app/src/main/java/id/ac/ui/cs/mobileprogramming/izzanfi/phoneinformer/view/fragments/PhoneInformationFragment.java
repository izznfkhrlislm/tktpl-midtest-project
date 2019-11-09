package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.adapters.DeviceSpecificationAdapter;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.SelectDeviceActivity;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.DeviceSpecificationViewModel;

public class PhoneInformationFragment extends Fragment {

    private List<DeviceSpecification> deviceSpecificationList = new ArrayList<>();
    private DeviceSpecificationAdapter dataAdapter;
    private DeviceSpecificationViewModel viewModel;
    DisplayMetrics dm;

    Button getFromApiBtn;
    Button editNameBtn;
    Button editContactInformationBtn;


    TextView brandText;
    TextView modelName;
    TextView chipsetName;
    TextView screenSize;
    TextView screenResolution;
    TextView totalRamAmount;
    TextView internalStorage;
    TextView phoneNumber;
    TextView provider;
    TextView signalStrength;

    public PhoneInformationFragment() {

    }

    public static PhoneInformationFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        PhoneInformationFragment fragment = new PhoneInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_phone_information, container, false);

        brandText = view.findViewById(R.id.phoneInformationFragmentBrandText);
        brandText.setText("Phone Manufacturer: " + Build.MANUFACTURER);

        modelName = view.findViewById(R.id.phoneInformationFragmentModelText);
        modelName.setText("Model: " + Build.MODEL);

        chipsetName = view.findViewById(R.id.phoneInformationFragmentChipsetText);
        chipsetName.setText("Board: " + Build.BOARD);

        screenSize = view.findViewById(R.id.phoneInformationFragmentScreenSizeText);
        screenSize.setText("Screen Size: " + getScreenSize());

        screenResolution = view.findViewById(R.id.phoneInformationFragmentScreenResolutionText);
        screenResolution.setText("Screen Resolution: ");

        totalRamAmount = view.findViewById(R.id.phoneInformationFragmentRamText);
        totalRamAmount.setText("RAM: ");

        internalStorage = view.findViewById(R.id.phoneInformationFragmentInternalStorageText);
        internalStorage.setText("Internal Storage: ");

        getFromApiBtn = view.findViewById(R.id.getFromApiBtn);
        getFromApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PhoneInformationFrag", "clicked!");
                openDialog();
            }
        });

        return view;
    }

    private void openDialog() {
        Intent intent = new Intent(getActivity(), SelectDeviceActivity.class);
        startActivityForResult(intent, 1);
    }

    @NonNull
    private String getScreenSize() {
        dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int density = dm.densityDpi;
        double wi = (double) width / (double) density;
        double hi = (double) height / (double) density;

        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenSize = Math.sqrt(x + y);

        return String.format("%.2f", screenSize) + " inches";
    }

}
