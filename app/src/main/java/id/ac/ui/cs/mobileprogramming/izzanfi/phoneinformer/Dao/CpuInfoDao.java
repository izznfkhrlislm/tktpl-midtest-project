package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.CpuInfo;

@Dao
public interface CpuInfoDao {
    @Insert
    void insert(CpuInfo cpuInfo);

    @Update
    void update(CpuInfo cpuInfo);

    @Query("DELETE FROM cpuInfo_table")
    void deleteAllCpuInfos();

    @Query("SELECT * FROM cpuInfo_table ORDER BY id")
    LiveData<List<CpuInfo>> getAllCpuInfos();
}
