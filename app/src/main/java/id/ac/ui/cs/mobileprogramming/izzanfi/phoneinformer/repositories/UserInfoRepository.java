package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.UserInfoDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.databases.DeviceSpecificationDatabase;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.UserInfo;

public class UserInfoRepository {
    private UserInfoDao userInfoDao;
    private LiveData<List<UserInfo>> allUserInfosData;

    public UserInfoRepository(Application application) {
        DeviceSpecificationDatabase database = DeviceSpecificationDatabase.getInstance(application);
        userInfoDao = database.userInfoDao();
        allUserInfosData = userInfoDao.getAllUserInfos();
    }

    public void insert(UserInfo userInfo) {
        new InsertUserInfoAsyncTask(userInfoDao).execute(userInfo);
    }

    public void update(UserInfo userInfo) {
        new UpdateUserInfoAsyncTask(userInfoDao).execute(userInfo);
    }

    public void deleteAll() {
        new DeleteAllUserInfoAsyncTask(userInfoDao).execute();
    }

    public LiveData<List<UserInfo>> getAllUserInfosData() {
        return allUserInfosData;
    }


    private static class InsertUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void> {
        private UserInfoDao userInfoDao;

        private InsertUserInfoAsyncTask(UserInfoDao userInfoDao) {
            System.out.println("insert executed");
            this.userInfoDao = userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            userInfoDao.insert(userInfos[0]);
            return null;
        }
    }

    private static class UpdateUserInfoAsyncTask extends AsyncTask<UserInfo, Void, Void> {
        private UserInfoDao userInfoDao;

        private UpdateUserInfoAsyncTask(UserInfoDao userInfoDao) {
            this.userInfoDao = userInfoDao;
        }

        @Override
        protected Void doInBackground(UserInfo... userInfos) {
            userInfoDao.update(userInfos[0]);
            return null;
        }
    }

    private static class DeleteAllUserInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserInfoDao userInfoDao;

        private DeleteAllUserInfoAsyncTask(UserInfoDao userInfoDao) {
            this.userInfoDao = userInfoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoDao.deleteAllUserInfos();
            return null;
        }
    }
}
