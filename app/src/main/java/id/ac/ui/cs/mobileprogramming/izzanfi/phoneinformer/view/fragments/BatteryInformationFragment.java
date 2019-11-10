package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.view.fragments;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.R;

public class BatteryInformationFragment extends Fragment {

    View view;

    TextView batteryPercentage;
    TextView batteryHealth;
    TextView powerSource;
    TextView batteryTechnology;
    TextView batteryTemperature;
    TextView batteryVoltage;
    TextView batteryCapacity;

    public BatteryInformationFragment() {

    }

    public static BatteryInformationFragment newInstance(String title, String description) {
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        BatteryInformationFragment fragment = new BatteryInformationFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_battery_information, container, false);

        batteryPercentage = view.findViewById(R.id.batteryInformationFragmentPercentage);
        batteryHealth = view.findViewById(R.id.batteryInformationFragmentHealth);
        powerSource = view.findViewById(R.id.batteryInformationFragmentSource);
        batteryTechnology = view.findViewById(R.id.batteryInformationFragmentTechnology);
        batteryTemperature = view.findViewById(R.id.batteryInformationFragmentTemperature);
        batteryVoltage = view.findViewById(R.id.batteryInformationFragmentVoltage);
        batteryCapacity = view.findViewById(R.id.batteryInformationFragmentCapacity);

        loadBatteryInfo();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(batteryInfoReceiver);
    }

    private void loadBatteryInfo() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        getContext().registerReceiver(batteryInfoReceiver, intentFilter);
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateBatteryData(intent);
        }
    };

    private void updateBatteryData(@NonNull Intent intent) {
        boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
        if (present) {
            int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            this.batteryHealth.setText("Battery Health: " + parseBatteryHealth(batteryHealth));

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            if (level != -1 && scale != -1) {
                int batteryPercentage = (int) ((level / (float) scale) * 100f);
                this.batteryPercentage.setText("Battery Percentage: " + batteryPercentage + " %");
            }

            int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            this.powerSource.setText("Power Source: " + parseBatteryStatus(batteryStatus));


            if (intent.getExtras() != null) {
                String batteryTechnology = intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
                this.batteryTechnology.setText("Technology: " + batteryTechnology);
            }

            int batteryTemp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            if (batteryTemp > 0) {
                float finalTemp = ((float) batteryTemp / 10f);
                this.batteryTemperature.setText("Battery Temperature: " + finalTemp + " C");
            }

            int batteryVoltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            this.batteryVoltage.setText("Battery Voltage: " + batteryVoltage + " mV");

            long batteryCapacity = getBatteryCapacity();
            this.batteryCapacity.setText("Capacity: " + batteryCapacity + " mAh");

        } else {
            Toast.makeText(getContext(), "No battery present!", Toast.LENGTH_SHORT).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private long getBatteryCapacity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getContext().getSystemService(Context.BATTERY_SERVICE);
            Integer chargeCounter = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            Integer capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

            if (chargeCounter == Integer.MIN_VALUE || capacity == Integer.MIN_VALUE) {
                return 0;
            }

            return (chargeCounter/capacity) * 100;
        }

        return 0;
    }

    private String parseBatteryHealth(int health) {
        String res = "";
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_COLD:
                res = "Cold";
                break;
            case BatteryManager.BATTERY_HEALTH_DEAD:
                res = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                res = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                res = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                res = "Overheat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                res = "Failure - Unspecified";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
            default:
                break;
        }
        return res;
    }

    private String parseBatteryStatus(int status) {
        String res = "";
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                res = "AC Charger";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                res = "Battery";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                res = "AC Charger - Battery Full";
                break;
            default:
                break;
        }
        return res;
    }
}
