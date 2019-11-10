package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "cpuInfo_table")
public class CpuInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    @SerializedName("DeviceName")
    @ColumnInfo(name = "device_name")
    private String deviceName;

    @Expose
    @SerializedName("chipset")
    @ColumnInfo(name = "cpu_model")
    private String cpuModel;

    @Expose
    @SerializedName("cpu")
    @ColumnInfo(name = "cpu_type")
    private String cpuType;

    @Expose
    @SerializedName("gpu")
    @ColumnInfo(name = "gpu_info")
    private String gpuInfo;

    public CpuInfo(String deviceName, String cpuModel, String cpuType, String gpuInfo) {
        this.cpuModel = cpuModel;
        this.cpuType = cpuType;
        this.gpuInfo = gpuInfo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public String getCpuType() {
        return cpuType;
    }

    public String getGpuInfo() {
        return gpuInfo;
    }
}

