package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.UserInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.SelectDeviceActivity;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.dialogs.InputContactInformationDialog;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.dialogs.InputUserFullNameDialog;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.DeviceSpecificationViewModel;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.UserInfoViewModel;

import static android.app.Activity.RESULT_OK;

public class PhoneInformationFragment extends Fragment implements InputUserFullNameDialog.OnInputReceived, InputContactInformationDialog.OnInputReceived {

    private DeviceSpecificationViewModel deviceSpecificationViewModel;
    private UserInfoViewModel userInfoViewModel;
    private DeviceSpecification currentModel;
    DisplayMetrics dm;
    private View view;

    Button getFromApiBtn;
    Button editNameBtn;
    Button editContactInformationBtn;
    Button getFromSystemBtn;

    TextView userFullName;
    TextView brandText;
    TextView modelName;
    TextView chipsetName;
    TextView screenSize;
    TextView screenResolution;
    TextView totalRamAmount;
    TextView internalStorage;
    TextView phoneNumberTv;
    TextView providerTv;

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
        view = inflater.inflate(R.layout.fragment_phone_information, container, false);
        getDataFromSystem();

        deviceSpecificationViewModel = ViewModelProviders.of(this).get(DeviceSpecificationViewModel.class);
        deviceSpecificationViewModel.init(getActivity().getApplication(), Build.MANUFACTURER, Build.MODEL);

        userInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        userInfoViewModel.init(getActivity().getApplication());
        userInfoViewModel.getAllUserInfos().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                if (!userInfos.isEmpty()) {
                    userFullName.setText(userInfos.get(0).getFullName() + "'s Phone");
                    if (userInfos.get(0).getPhoneNumber() != null && userInfos.get(0).getPhoneNumber() != null) {
                        phoneNumberTv.setText("Phone Number: " + userInfos.get(0).getPhoneNumber());
                        providerTv.setText("Provider: " + userInfos.get(0).getProviderName());
                    }
                }
            }
        });

        phoneNumberTv = view.findViewById(R.id.phoneInformationFragmentPhoneNumberText);
        userFullName = view.findViewById(R.id.phoneInformationFragmentUserNameText);
        providerTv = view.findViewById(R.id.phoneInformationFragmentProviderNameText);

        getFromApiBtn = view.findViewById(R.id.getFromApiBtn);
        getFromApiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        getFromSystemBtn = view.findViewById(R.id.getFromSystemBtn);
        getFromSystemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Phone info from system selected!", Toast.LENGTH_SHORT).show();
                getDataFromSystem();
            }
        });

        editNameBtn = view.findViewById(R.id.editNameBtn);
        editNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditInfoDialog();
            }
        });

        editContactInformationBtn = view.findViewById(R.id.EditContactInfoBtn);
        editContactInformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditContactDialog();
            }
        });

        return view;
    }

    @Override
    public void sendContactInput(final String phoneNumber, final String providerName) {
        userInfoViewModel.getAllUserInfos().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfos) {
                if (!userInfos.isEmpty()) {
                    UserInfo ui = userInfos.get(0);
                    ui.setPhoneNumber(phoneNumber);
                    ui.setProviderName(providerName);
                    userInfoViewModel.update(ui);
                    phoneNumberTv.setText("Phone Number: " + userInfos.get(0).getPhoneNumber());
                    providerTv.setText("Provider: " + userInfos.get(0).getProviderName());
                }
            }
        });
        Toast.makeText(getContext(), "Contact information created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendInput(String input) {
        UserInfo ui = new UserInfo(input);
        userInfoViewModel.insert(ui);
        userFullName.setText(input + "'s Phone");
        Toast.makeText(getContext(), "User info created!", Toast.LENGTH_SHORT).show();
    }

    private void openEditContactDialog() {
        InputContactInformationDialog dialog = new InputContactInformationDialog();
        dialog.setTargetFragment(PhoneInformationFragment.this, 1);
        dialog.show(getFragmentManager(), "Dialog");
    }

    private void openEditInfoDialog() {
        InputUserFullNameDialog dialog = new InputUserFullNameDialog();
        dialog.setTargetFragment(PhoneInformationFragment.this, 1);
        dialog.show(getFragmentManager(), "Dialog");
    }

    private void openDialog() {
        Intent intent = new Intent(getActivity(), SelectDeviceActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                DeviceSpecification res = (DeviceSpecification) data.getSerializableExtra("selectedObj");
                deviceSpecificationViewModel.insert(res);
                currentModel = res;
                setDataFromAPI(currentModel);
            }
        }
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

    @NonNull
    private String getScreenResolution() {
        dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        return width + "x" + height;
    }

    @NonNull
    private String getTotalRAM() {
        ActivityManager am = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memInfo);
        long totalMem = memInfo.totalMem;

        return (totalMem / (1024 * 1024)) + " MB";
    }

    private String getInternalStorageSize() {
        File path = Environment.getDataDirectory();
        StatFs fs = new StatFs(path.getPath());
        long blockSize = fs.getBlockSizeLong();
        long availableBlockSize = fs.getBlockCountLong();
        long totalInternalMem = availableBlockSize * blockSize;

        return (totalInternalMem / (1024 * 1024)) + " MB";
    }

    private void setDataFromAPI(DeviceSpecification ds) {
        TextView brandText1 = view.findViewById(R.id.phoneInformationFragmentBrandText);
        brandText1.setText("Phone Manufacturer: " + ds.getBrand());

        TextView modelName1 = view.findViewById(R.id.phoneInformationFragmentModelText);
        modelName1.setText("Model: " + ds.getDeviceName());

        TextView chipsetName1 = view.findViewById(R.id.phoneInformationFragmentChipsetText);
        chipsetName1.setText("Board: " + ds.getChipset());

        TextView screenSize1 = view.findViewById(R.id.phoneInformationFragmentScreenSizeText);
        screenSize1.setText("Screen Size: " + ds.getScreenSize());

        TextView screenResolution1 = view.findViewById(R.id.phoneInformationFragmentScreenResolutionText);
        screenResolution1.setText("Screen Resolution: " + ds.getScreenResolution());

        TextView totalRamAmount1 = view.findViewById(R.id.phoneInformationFragmentRamText);
        totalRamAmount1.setText("RAM: " + ds.getRamAndInternalStorageAmount().split(",")[1]);

        TextView internalStorage1 = view.findViewById(R.id.phoneInformationFragmentInternalStorageText);
        internalStorage1.setText("Internal Storage: " + ds.getRamAndInternalStorageAmount().split(",")[0]);
    }

    private void getDataFromSystem() {
        brandText = view.findViewById(R.id.phoneInformationFragmentBrandText);
        brandText.setText("Phone Manufacturer: " + Build.MANUFACTURER);

        modelName = view.findViewById(R.id.phoneInformationFragmentModelText);
        modelName.setText("Model: " + Build.MODEL);

        chipsetName = view.findViewById(R.id.phoneInformationFragmentChipsetText);
        chipsetName.setText("Board: " + Build.BOARD);

        screenSize = view.findViewById(R.id.phoneInformationFragmentScreenSizeText);
        screenSize.setText("Screen Size: " + getScreenSize());

        screenResolution = view.findViewById(R.id.phoneInformationFragmentScreenResolutionText);
        screenResolution.setText("Screen Resolution: " + getScreenResolution());

        totalRamAmount = view.findViewById(R.id.phoneInformationFragmentRamText);
        totalRamAmount.setText("RAM: " + getTotalRAM());

        internalStorage = view.findViewById(R.id.phoneInformationFragmentInternalStorageText);
        internalStorage.setText("Internal Storage: " + getInternalStorageSize());
    }
}
