package kg.coder.placeholderproject.data.network;

import kg.coder.placeholderproject.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkBuilder {
    private static ApiService sService = null;

    public static ApiService initService() {
        if (sService == null) {
            sService = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return sService;
    }
}