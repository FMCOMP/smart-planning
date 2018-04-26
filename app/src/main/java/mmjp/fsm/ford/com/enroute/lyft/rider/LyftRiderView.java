package mmjp.fsm.ford.com.enroute.lyft.rider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import mmjp.fsm.ford.com.enroute.lyft.rider.entity.LyftAuth;
import mmjp.fsm.ford.com.enroute.lyft.rider.entity.LyftClientToken;

public class LyftRiderView extends AppCompatActivity implements LyftRiderContract.View {

    private Unbinder unbinder;
    private LyftRiderContract.Presenter rider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);

        LyftRiderContract.Service service = new LyftRiderService();
        service.onCreate(this);
        service.fetchClientAuth(new LyftAuth())
                .subscribe(new Consumer<LyftClientToken>() {
                    @Override
                    public void accept(LyftClientToken lyftClientToken) throws Exception {
                        LyftClientToken token = lyftClientToken;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
//                        mField = field;
                    }
                });

//        rider = new LyftRiderPresenter();
//        rider.onCreate(this);
//        rider.deepLinkIntoLyft();
    }

    @Override
    public void onResume() {
        super.onResume();

        System.out.println("Lyft Resumed");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("Lyft Activity Result");
    }

    public void onRiderRequestListener(LyftRiderContract.View mListener){

    }

}
