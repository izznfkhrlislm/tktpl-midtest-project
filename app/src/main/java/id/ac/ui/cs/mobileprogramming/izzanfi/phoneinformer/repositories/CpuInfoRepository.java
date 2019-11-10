package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.CpuInfoDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.databases.DeviceSpecificationDatabase;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.CpuInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils.ApiCallUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CpuInfoRepository {
    private CpuInfoDao cpuInfoDao;
    private LiveData<List<CpuInfo>> allCpuInfos;
    private ApiCallUtils.APIInterface apiInterface;

    public CpuInfoRepository(Application application) {
        DeviceSpecificationDatabase database = DeviceSpecificationDatabase.getInstance(application);
        cpuInfoDao = database.cpuInfoDao();
        allCpuInfos = cpuInfoDao.getAllCpuInfos();
    }

    public void insert(CpuInfo cpuInfo) {
        new InsertCpuInfoAsyncTask(cpuInfoDao).execute(cpuInfo);
    }

    public void update(CpuInfo cpuInfo) {
        new UpdateCpuInfoAsyncTask(cpuInfoDao).execute(cpuInfo);
    }

    public void deleteAll() {
        new DeleteAllCpuInfoAsyncTask(cpuInfoDao).execute();
    }

    public LiveData<List<CpuInfo>> getAllCpuInfos() {
        return allCpuInfos;
    }

    public MutableLiveData<List<CpuInfo>> getAllCpuInfosFromApi(String brand, String model) {
        final MutableLiveData<List<CpuInfo>> responseData = new MutableLiveData<>();
        apiInterface = ApiCallUtils.getAPIInterfaceInstance();
        Call<List<CpuInfo>> call = apiInterface.getCpuInfoByBrandAndDevice(
                ApiCallUtils.TOKEN, brand, model);
        call.enqueue(new Callback<List<CpuInfo>>() {
            @Override
            public void onResponse(Call<List<CpuInfo>> call, Response<List<CpuInfo>> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body());
                } else {
                    Log.e("Get From API", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<CpuInfo>> call, Throwable t) {
                responseData.setValue(null);
            }
        });

        return responseData;
    }

    private static class InsertCpuInfoAsyncTask extends AsyncTask<CpuInfo, Void, Void> {
        private CpuInfoDao cpuInfoDao;

        private InsertCpuInfoAsyncTask(CpuInfoDao cpuInfoDao) {
            System.out.println("insert executed");
            this.cpuInfoDao = cpuInfoDao;
        }

        @Override
        protected Void doInBackground(CpuInfo... cpuInfos) {
            cpuInfoDao.insert(cpuInfos[0]);
            return null;
        }
    }

    private static class UpdateCpuInfoAsyncTask extends AsyncTask<CpuInfo, Void, Void> {
        private CpuInfoDao cpuInfoDao;

        private UpdateCpuInfoAsyncTask(CpuInfoDao cpuInfoDao) {
            this.cpuInfoDao = cpuInfoDao;
        }

        @Override
        protected Void doInBackground(CpuInfo... cpuInfos) {
            cpuInfoDao.update(cpuInfos[0]);
            return null;
        }
    }

    private static class DeleteAllCpuInfoAsyncTask extends AsyncTask<Void, Void, Void> {
        private CpuInfoDao cpuInfoDao;

        private DeleteAllCpuInfoAsyncTask(CpuInfoDao cpuInfoDao) {
            this.cpuInfoDao = cpuInfoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cpuInfoDao.deleteAllCpuInfos();
            return null;
        }
    }
}
