package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils.ApiCallUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceSpecifications extends BaseObservable {

    private static final String TAG = "DeviceSpecifications";

    private List<DeviceSpecification> deviceSpecifications;
    private MutableLiveData<List<DeviceSpecification>> specifications = new MutableLiveData<>();

    public DeviceSpecifications() {

    }

    public DeviceSpecifications(List<DeviceSpecification> deviceSpecifications) {
        this.deviceSpecifications = deviceSpecifications;
    }

    public List<DeviceSpecification> getDeviceSpecifications() {
        return deviceSpecifications;
    }

    public void setDeviceSpecifications(List<DeviceSpecification> deviceSpecifications) {
        this.deviceSpecifications = deviceSpecifications;
    }

    public MutableLiveData<List<DeviceSpecification>> getSpecifications() {
        return specifications;
    }

    public void fetchList(String brand, String model) {
        Callback<DeviceSpecifications> callback = new Callback<DeviceSpecifications>() {
            @Override
            public void onResponse(Call<DeviceSpecifications> call,
                                   Response<DeviceSpecifications> response) {
                Log.d(TAG, "Response Body: " + response.body().getDeviceSpecifications()
                        .toString());
                DeviceSpecifications body = response.body();
                specifications.setValue(body.deviceSpecifications);
            }

            @Override
            public void onFailure(Call<DeviceSpecifications> call, Throwable t) {
                Log.e(TAG, "Exception: " + t.getMessage(), t);
            }
        };

        ApiCallUtils.getAPIInterface()
                .getDeviceSpecificationsByBrandAndDevice(brand, model).enqueue(callback);
    }
}
