package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.scottyab.rootbeer.RootBeer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class SystemInformationFragment extends Fragment {

    View view;

    TextView androidVersion;
    TextView rootStatus;
    TextView apiLevel;
    TextView buildId;
    TextView kernelVersion;
    TextView systemUptime;

    private Handler mainHandler = new Handler();
    private long systemUptimeInMillis = SystemClock.uptimeMillis();

    public SystemInformationFragment() {

    }

    public static SystemInformationFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        SystemInformationFragment fragment = new SystemInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_system_information, container, false);

        androidVersion = view.findViewById(R.id.systemInformationFragmentAndroidVersion);
        rootStatus = view.findViewById(R.id.systemInformationFragmentRootStatus);
        buildId = view.findViewById(R.id.systemInformationFragmentBuildId);
        apiLevel = view.findViewById(R.id.systemInformationFragmentApiLevel);
        kernelVersion = view.findViewById(R.id.systemInformationFragmentSecurityPatch);
        systemUptime = view.findViewById(R.id.systemInformationFragmentSystemUptime);

        loadSystemInfo();

        return view;
    }

    private void loadSystemInfo() {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String androidOsName = parseAndroidOsName(fields[Build.VERSION.SDK_INT].getName());
        String androidVersion = Build.VERSION.RELEASE;
        this.androidVersion.setText("Android " + androidVersion + " (" + androidOsName + ")");

        RootBeer rb = new RootBeer(getContext());
        if (rb.isRooted() || rb.isRootedWithoutBusyBoxCheck()) {
            this.rootStatus.setText("Root Status: Yes");
        } else {
            this.rootStatus.setText("Root Status: No");
        }

        int apiLevel = Build.VERSION.SDK_INT;
        this.apiLevel.setText("API Level: " + apiLevel);

        String buildId = Build.ID;
        this.buildId.setText("Build ID: " + buildId);

        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                systemUptimeInMillis += 1000;
                Date date = new Date(systemUptimeInMillis);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                String dateFormatted = formatter.format(date);
                systemUptime.setText("System Uptime: " + dateFormatted);
                mainHandler.postDelayed(this, 1000);
            }
        });

        String kernelVersion = readKernelVersion();
        this.kernelVersion.setText("Kernel Version: " + kernelVersion);

    }

    private String parseAndroidOsName(String osName) {
        String res = "";
        if (osName.contains("M")) res = "Marshmallow";
        if (osName.contains("N")) res = "Nougat";
        if (osName.contains("O")) res = "Oreo";
        if (osName.contains("P")) res = "Pie";
        return res;
    }

    public static String readKernelVersion() {
        try {
            Process p = Runtime.getRuntime().exec("uname -a");
            InputStream is = null;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is), 1024);
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }
}
