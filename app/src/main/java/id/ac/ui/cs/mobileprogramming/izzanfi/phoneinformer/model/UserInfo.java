package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Entity(tableName = "userInfo_table")
public class UserInfo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    @ColumnInfo(name = "full_name")
    private String fullName;

    @Expose
    @ColumnInfo(name = "provider_name", defaultValue = "Not yet set")
    private String providerName = "Not yet set";

    @Expose
    @ColumnInfo(name = "phone_number", defaultValue = "Not yet set")
    private String phoneNumber = "Not yet set";

    public UserInfo(String fullName) {
        this.fullName = fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
