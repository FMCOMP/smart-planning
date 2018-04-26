package mmjp.fsm.ford.com.enroute.lyft.rider;

import android.app.Activity;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import mmjp.fsm.ford.com.enroute.lyft.rider.entity.LyftAuth;
import mmjp.fsm.ford.com.enroute.lyft.rider.entity.LyftClientToken;
import mmjp.fsm.ford.com.enroute.lyft.rider.utils.LyftConstants;
import mmjp.fsm.ford.com.services.api.APIRequestService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LyftRiderService implements LyftRiderContract.Service {

    private String mVersion;
    private String base_url;
    private static Retrofit service;
    private static OkHttpClient client;
    private static APIRequestService instance = null;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    @Override
    public void onCreate(Activity mView) {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        client = builder
                .addInterceptor(interceptor)
                .build();

        service = new Retrofit.Builder().baseUrl(LyftConstants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public AsyncSubject<LyftClientToken> fetchClientAuth(LyftAuth auth) {
        AsyncSubject<LyftClientToken> subject = AsyncSubject.create();

        LyftRiderContract.Query query = service.create(LyftRiderContract.Query.class);

        query.fetchClientAuth(auth.getClientID(), auth.getResponseType(), auth.getScopes(), auth.getState())
                .subscribeOn(Schedulers.io())
                .subscribe(subject);

        return subject;
    }

    @Override
    public AsyncSubject<LyftClientToken> fetchClientToken() {
        AsyncSubject<LyftClientToken> subject = AsyncSubject.create();

        LyftRiderContract.Query query = service.create(LyftRiderContract.Query.class);


        query.fetchClientToken()
                .subscribeOn(Schedulers.io())
                .subscribe(subject);

        return subject;
    }

}
