package mmjp.fsm.ford.com.mmjp.app.route.providers.uber.modules.rider;

import com.uber.sdk.android.core.auth.LoginManager;

import mmjp.fsm.ford.com.mmjp.services.api.APIRequestService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UberRiderService implements UberRiderContract.Service {

    private String mVersion;
    private String base_url;
    private static Retrofit service;
    private static OkHttpClient client;
    private static APIRequestService instance = null;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public Retrofit UberRiderService() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        client = builder
                .addInterceptor(interceptor)
                .build();

        return new Retrofit.Builder().baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public void onCreate(UberRiderContract.View view) {

    }

    @Override
    public void loadProfileInfo(LoginManager loginManager) {

    }
}
