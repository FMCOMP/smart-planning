package mmjp.fsm.ford.com.mmjp.app.registration;

/**
 * Created by userpmp on 2/15/18.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view ;

    public RegistrationPresenter(RegistrationContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
       //Add Service Call logic Here
    }
}
