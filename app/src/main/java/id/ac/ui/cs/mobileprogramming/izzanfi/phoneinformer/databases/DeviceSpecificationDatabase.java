package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.CpuInfoDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.DeviceSpecificationDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.UserInfoDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.CpuInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.UserInfo;

@Database(entities = {DeviceSpecification.class, UserInfo.class, CpuInfo.class}, version = 1, exportSchema = false)
public abstract class DeviceSpecificationDatabase extends RoomDatabase {

    private static DeviceSpecificationDatabase instance;

    public abstract DeviceSpecificationDao deviceSpecificationDao();
    public abstract UserInfoDao userInfoDao();
    public abstract CpuInfoDao cpuInfoDao();

    public static synchronized DeviceSpecificationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DeviceSpecificationDatabase.class, "deviceSpecification_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
