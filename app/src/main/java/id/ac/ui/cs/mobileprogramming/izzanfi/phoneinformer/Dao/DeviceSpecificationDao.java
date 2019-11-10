package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;

@Dao
public interface DeviceSpecificationDao {

    @Insert
    void insert(DeviceSpecification deviceSpecification);

    @Update
    void update(DeviceSpecification deviceSpecification);

    @Query("DELETE FROM deviceSpecification_table")
    void deleteAllDeviceSpecifications();

    @Query("SELECT * FROM deviceSpecification_table ORDER BY id")
    LiveData<List<DeviceSpecification>> getAllDeviceSpecifications();
}
