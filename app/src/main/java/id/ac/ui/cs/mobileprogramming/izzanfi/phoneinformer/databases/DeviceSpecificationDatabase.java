package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.DeviceSpecificationDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;

@Database(entities = DeviceSpecification.class, version = 1)
public abstract class DeviceSpecificationDatabase extends RoomDatabase {

    private static DeviceSpecificationDatabase instance;

    public abstract DeviceSpecificationDao deviceSpecificationDao();

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
