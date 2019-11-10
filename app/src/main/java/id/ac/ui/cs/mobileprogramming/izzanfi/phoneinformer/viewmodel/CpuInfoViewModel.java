package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.CpuInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories.CpuInfoRepository;

public class CpuInfoViewModel extends ViewModel {
    private CpuInfoRepository repository;
    private LiveData<List<CpuInfo>> allCpuInfos;
    private MutableLiveData<List<CpuInfo>> mutableLiveData;

    public void init(Application application, String brand, String model) {
        repository = new CpuInfoRepository(application);
        allCpuInfos = repository.getAllCpuInfos();
        mutableLiveData = repository.getAllCpuInfosFromApi(brand, model);
    }

    public LiveData<List<CpuInfo>> getAllCpuInfos() {
        return allCpuInfos;
    }

    public MutableLiveData<List<CpuInfo>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void insert(CpuInfo ci) {
        repository.insert(ci);
    }

    public void update(CpuInfo ci) {
        repository.update(ci);
    }

    public void deleteAllCpuInfos() {
        repository.deleteAll();
    }
}
