package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api.DeviceSpecification;
import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api.DeviceSpecifications;

public class JsonDeviceSpecificationsDeserializer implements JsonDeserializer<DeviceSpecifications> {

    private static final String TAG = "JsonDeserializer";

    @Override
    public DeviceSpecifications deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        DeviceSpecifications result = new DeviceSpecifications();
        List<DeviceSpecification> deviceSpecificationList = new ArrayList<>();
        if (json.isJsonObject()) {
            for (JsonElement jsonElement : json.getAsJsonArray()) {
                JsonObject object = jsonElement.getAsJsonObject();
                Log.d(TAG, "JsonObject: " + object.toString());

                for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                    DeviceSpecification ds = new DeviceSpecification();
                    if (entry.getKey().equals("Brand")) {
                        ds.setBrand(entry.getValue().getAsString());
                    } else if (entry.getKey().equals("DeviceName")) {
                        ds.setDeviceName(entry.getValue().getAsString());
                    } else if (entry.getKey().equals("chipset")) {
                        ds.setChipset(entry.getValue().getAsString());
                    } else if (entry.getKey().equals("size")) {
                        ds.setScreenSize(entry.getValue().getAsString());
                    } else if (entry.getKey().equals("resolution")) {
                        ds.setScreenResolution(entry.getValue().getAsString());
                    } else if (entry.getKey().equals("internal")) {
                        ds.setRamAndInternalStorageAmount(entry.getValue().getAsString());
                    }

                    deviceSpecificationList.add(ds);
                }

            }
            result.setDeviceSpecifications(deviceSpecificationList);
        }
        return result;
    }
}
