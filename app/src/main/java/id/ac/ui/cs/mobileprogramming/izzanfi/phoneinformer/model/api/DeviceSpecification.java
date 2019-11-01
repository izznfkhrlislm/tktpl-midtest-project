package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api;

import com.google.gson.annotations.SerializedName;

public class DeviceSpecification {

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

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getRamAndInternalStorageAmount() {
        return ramAndInternalStorageAmount;
    }

    public void setRamAndInternalStorageAmount(String ramAndInternalStorageAmount) {
        this.ramAndInternalStorageAmount = ramAndInternalStorageAmount;
    }
}
