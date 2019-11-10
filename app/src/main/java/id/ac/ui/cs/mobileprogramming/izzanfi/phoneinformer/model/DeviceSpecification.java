package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "deviceSpecification_table")
public class DeviceSpecification implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    @SerializedName("Brand")
    @ColumnInfo(name = "brand")
    private String brand;

    @Expose
    @SerializedName("DeviceName")
    @ColumnInfo(name = "device_name")
    private String deviceName;

    @Expose
    @SerializedName("chipset")
    @ColumnInfo(name = "chipset")
    private String chipset;

    @Expose
    @SerializedName("size")
    @ColumnInfo(name = "screen_size")
    private String screenSize;

    @Expose
    @SerializedName("resolution")
    @ColumnInfo(name = "screen_resolution")
    private String screenResolution;

    @Expose
    @SerializedName("internal")
    @ColumnInfo(name = "memory")
    private String ramAndInternalStorageAmount;

    public DeviceSpecification(String brand, String deviceName, String chipset, String screenSize,
                               String screenResolution, String ramAndInternalStorageAmount) {
        this.brand = brand;
        this.deviceName = deviceName;
        this.chipset = chipset;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.ramAndInternalStorageAmount = ramAndInternalStorageAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
