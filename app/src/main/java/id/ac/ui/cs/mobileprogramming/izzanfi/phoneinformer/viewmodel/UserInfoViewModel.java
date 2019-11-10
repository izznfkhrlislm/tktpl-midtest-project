package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.UserInfo;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.repositories.UserInfoRepository;

public class UserInfoViewModel extends ViewModel {
    private UserInfoRepository repository;
    private LiveData<List<UserInfo>> allUserInfos;

    public void init(Application application) {
        repository = new UserInfoRepository(application);
        allUserInfos = repository.getAllUserInfosData();
    }

    public LiveData<List<UserInfo>> getAllUserInfos() {
        return allUserInfos;
    }

    public void insert(UserInfo ui) {
        repository.insert(ui);
    }

    public void update(UserInfo ui) {
        repository.update(ui);
    }

    public void deleteAllUserInfos() {
        repository.deleteAll();
    }
}
