package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.model.local.DeviceSpecification;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiCallUtils {

    private static APIInterface api;
    private static final String BASE_URL = "https://fonoapi.freshpixl.com/v1/";
    public static final String TOKEN = "38d392c9f7cd746ef7206d496171b83ff34c231ed3430259";

    public static APIInterface getAPIInterfaceInstance() {
        if (api == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            Retrofit retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofitInstance.create(APIInterface.class);
        }

        return api;
    }

    public interface APIInterface {

        @GET("getdevice")
        Call<List<DeviceSpecification>> getDeviceSpecificationsByBrandAndDevice(
                @Query("token") String token, @Query("brand") String brand,
                @Query("device") String model);
    }
}
