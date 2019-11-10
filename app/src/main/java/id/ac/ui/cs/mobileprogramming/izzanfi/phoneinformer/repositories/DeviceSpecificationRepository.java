package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.Dao.DeviceSpecificationDao;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.databases.DeviceSpecificationDatabase;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils.ApiCallUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceSpecificationRepository {
    private DeviceSpecificationDao deviceSpecificationDao;
    private LiveData<List<DeviceSpecification>> allDeviceSpecifications;
    private ApiCallUtils.APIInterface apiInterface;

    public DeviceSpecificationRepository(Application application) {
        DeviceSpecificationDatabase database = DeviceSpecificationDatabase.getInstance(application);
        deviceSpecificationDao = database.deviceSpecificationDao();
        allDeviceSpecifications = deviceSpecificationDao.getAllDeviceSpecifications();
    }

    public void insert(DeviceSpecification deviceSpecification) {
        new InsertDeviceSpecificationAsyncTask(deviceSpecificationDao).execute(deviceSpecification);
    }

    public void update(DeviceSpecification deviceSpecification) {
        new UpdateDeviceSpecificationAsyncTask(deviceSpecificationDao).execute(deviceSpecification);
    }

    public void deleteAll() {
        new DeleteAllDeviceSpecificationAsyncTask(deviceSpecificationDao).execute();
    }

    public LiveData<List<DeviceSpecification>> getAllDeviceSpecifications() {
        return allDeviceSpecifications;
    }

    public MutableLiveData<List<DeviceSpecification>> getDeviceSpecificationListFromAPI(String brand, String model) {
        final MutableLiveData<List<DeviceSpecification>> responseData = new MutableLiveData<>();
        apiInterface = ApiCallUtils.getAPIInterfaceInstance();
        Call<List<DeviceSpecification>> call = apiInterface.getDeviceSpecificationsByBrandAndDevice(
                ApiCallUtils.TOKEN, brand, model);
        call.enqueue(new Callback<List<DeviceSpecification>>() {
            @Override
            public void onResponse(Call<List<DeviceSpecification>> call, Response<List<DeviceSpecification>> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body());
                } else {
                    Log.e("Get From API", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<DeviceSpecification>> call, Throwable t) {
                responseData.setValue(null);
            }
        });

        return responseData;
    }

    private static class InsertDeviceSpecificationAsyncTask extends AsyncTask<DeviceSpecification, Void, Void> {
        private DeviceSpecificationDao deviceSpecificationDao;

        private InsertDeviceSpecificationAsyncTask(DeviceSpecificationDao deviceSpecificationDao) {
            System.out.println("insert executed");
            this.deviceSpecificationDao = deviceSpecificationDao;
        }

        @Override
        protected Void doInBackground(DeviceSpecification... deviceSpecifications) {
            deviceSpecificationDao.insert(deviceSpecifications[0]);
            return null;
        }
    }

    private static class UpdateDeviceSpecificationAsyncTask extends AsyncTask<DeviceSpecification, Void, Void> {
        private DeviceSpecificationDao deviceSpecificationDao;

        private UpdateDeviceSpecificationAsyncTask(DeviceSpecificationDao deviceSpecificationDao) {
            this.deviceSpecificationDao = deviceSpecificationDao;
        }

        @Override
        protected Void doInBackground(DeviceSpecification... deviceSpecifications) {
            deviceSpecificationDao.update(deviceSpecifications[0]);
            return null;
        }
    }

    private static class DeleteAllDeviceSpecificationAsyncTask extends AsyncTask<Void, Void, Void> {
        private DeviceSpecificationDao deviceSpecificationDao;

        private DeleteAllDeviceSpecificationAsyncTask(DeviceSpecificationDao deviceSpecificationDao) {
            this.deviceSpecificationDao = deviceSpecificationDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            deviceSpecificationDao.deleteAllDeviceSpecifications();
            return null;
        }
    }
}
