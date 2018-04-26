package mmjp.fsm.ford.com.services.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequestService implements APIRequestInterface {

    private String mVersion;
    private String base_url;
    private static OkHttpClient client;
    private static Retrofit retrofitAPI;
    private static APIRequestService instance = null;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    public void APIRequestService() {}

    public Retrofit getPCFRequestService() {
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

    public void setAPIRequest(String version){
    switch (version){
        case "v1":
            base_url = APIRequestConstants.BASE_URL_V1;
            break;
        case "v2":
            base_url = APIRequestConstants.BASE_URL_V1;
            break;
        default:
            base_url = APIRequestConstants.BASE_URL_V1;
            break;
    }

    mVersion = version;
}
}
