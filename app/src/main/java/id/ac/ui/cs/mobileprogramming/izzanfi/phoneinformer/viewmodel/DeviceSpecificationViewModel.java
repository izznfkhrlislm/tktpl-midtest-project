package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories.DeviceSpecificationRepository;

public class DeviceSpecificationViewModel extends ViewModel {
    private DeviceSpecificationRepository repository;
    private LiveData<List<DeviceSpecification>> allDeviceSpecifications;
    private MutableLiveData<List<DeviceSpecification>> mutableLiveData;

    public void init(Application application, String brand, String model) {
        repository = new DeviceSpecificationRepository(application);
        allDeviceSpecifications = repository.getAllDeviceSpecifications();
        mutableLiveData = repository.getDeviceSpecificationListFromAPI(brand, model);
    }

    public MutableLiveData<List<DeviceSpecification>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void insert(DeviceSpecification ds) {
        repository.insert(ds);
    }

    public void update(DeviceSpecification ds) {
        repository.update(ds);
    }

    public void deleteAllDeviceSpecifications() {
        repository.deleteAll();
    }

    public LiveData<List<DeviceSpecification>> getAllDeviceSpecifications() {
        return allDeviceSpecifications;
    }
}
