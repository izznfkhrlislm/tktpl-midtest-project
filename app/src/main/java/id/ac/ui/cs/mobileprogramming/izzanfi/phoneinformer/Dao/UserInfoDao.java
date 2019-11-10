package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.UserInfo;

@Dao
public interface UserInfoDao {
    @Insert
    void insert(UserInfo userInfo);

    @Update
    void update(UserInfo userInfo);

    @Query("DELETE FROM userInfo_table")
    void deleteAllUserInfos();

    @Query("SELECT * FROM userInfo_table ORDER BY id")
    LiveData<List<UserInfo>> getAllUserInfos();
}
