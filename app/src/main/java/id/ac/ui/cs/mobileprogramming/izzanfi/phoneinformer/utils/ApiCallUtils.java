package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.api.DeviceSpecifications;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiCallUtils {

    private static APIInterface api;
    private static final String BASE_URL = "https://fonoapi.freshpixl.com/v1";
    private static final String TOKEN = "38d392c9f7cd746ef7206d496171b83ff34c231ed3430259";

    public static APIInterface getAPIInterface() {
        if (api == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(
                            DeviceSpecifications.class,
                            new JsonDeviceSpecificationsDeserializer())
                    .create();

            Retrofit retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            api = retrofitInstance.create(APIInterface.class);
        }

        return api;
    }

    public interface APIInterface {

        @GET("/getdevice?token=" + TOKEN + "&brand={brand}&device={model}")
        Call<DeviceSpecifications> getDeviceSpecificationsByBrandAndDevice(
                @Path("brand") String brand, @Path("model") String model);
    }
}
