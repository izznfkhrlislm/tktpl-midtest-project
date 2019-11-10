package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.CpuInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel.CpuInfoViewModel;

public class CpuInformationFragment extends Fragment {

    private CpuInfoViewModel viewModel;
    private View view;

    TextView cpuName;
    TextView cpuClockSpeed;
    TextView cpuCoreCount;
    TextView gpuName;

    View loadingAnimation;
    View contents;

    public CpuInformationFragment() {

    }

    public static CpuInformationFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        CpuInformationFragment fragment = new CpuInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_cpu_information, container, false);

        cpuName = view.findViewById(R.id.cpuInformationFragmentCpuName);
        cpuClockSpeed = view.findViewById(R.id.cpuInformationFragmentClockSpeed);
        cpuCoreCount = view.findViewById(R.id.cpuInformationFragmentCoresCount);
        gpuName = view.findViewById(R.id.cpuInformationFragmentGpuName);
        loadingAnimation = view.findViewById(R.id.loadingView);
        contents = view.findViewById(R.id.cpuFragmentContent);

        viewModel = ViewModelProviders.of(this).get(CpuInfoViewModel.class);
        contents.setVisibility(View.GONE);
        loadingAnimation.setVisibility(View.VISIBLE);
        viewModel.init(getActivity().getApplication(), Build.MANUFACTURER, Build.MODEL);
        viewModel.getMutableLiveData().observe(this, new Observer<List<CpuInfo>>() {
            @Override
            public void onChanged(List<CpuInfo> cpuInfos) {
                loadingAnimation.setVisibility(View.GONE);
                contents.setVisibility(View.VISIBLE);
                if (cpuInfos != null) {
                    for (CpuInfo cpuInfo : cpuInfos) {
                        if (cpuInfo.getDeviceName().equals(Build.MANUFACTURER + " " + Build.MODEL)) {
                            setData(cpuInfo);
                        }
                    }
                }
            }
        });

        return view;
    }

    public void setData(CpuInfo cpuInfo) {
        cpuName.setText(cpuInfo.getCpuModel());
        cpuClockSpeed.setText("Clock Speed: " + cpuInfo.getCpuType().split(" ")[1]);
        cpuCoreCount.setText("CPU Core Type: " + cpuInfo.getCpuType().split(" ")[0]);
        gpuName.setText("GPU Name: " + cpuInfo.getGpuInfo());
    }
}
