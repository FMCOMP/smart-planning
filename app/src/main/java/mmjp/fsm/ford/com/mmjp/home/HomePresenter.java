package mmjp.fsm.ford.com.mmjp.app.home;

/**
 * Created by mfayajna on 4/2/18.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view ;
    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
       //Add Service Call logic Here
    }
}
