package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils.ApiCallUtils;
import retrofit2.Callback;

public class DeviceSpecification extends BaseObservable {

    @SerializedName("Brand")
    private String brand;

    @SerializedName("DeviceName")
    private String deviceName;

    @SerializedName("chipset")
    private String chipset;

    @SerializedName("size")
    private String screenSize;

    @SerializedName("resolution")
    private String screenResolution;

    @SerializedName("internal")
    private String ramAndInternalStorageAmount;

    public DeviceSpecification() {

    }

    public DeviceSpecification(String brand, String deviceName, String chipset,
                               String screenSize, String screenResolution,
                               String ramAndInternalStorageAmount) {
        this.brand = brand;
        this.deviceName = deviceName;
        this.chipset = chipset;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.ramAndInternalStorageAmount = ramAndInternalStorageAmount;
    }

    public String getBrand() {
        return brand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getChipset() {
        return chipset;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public String getRamAndInternalStorageAmount() {
        return ramAndInternalStorageAmount;
    }
}
