package mmjp.fsm.ford.com.mmjp.app.forgetpasscode;

/**
 * Created by mfayajna on 4/3/18.
 */

public class ForgetPasscodePresenter implements ForgetPasscodeContract.Presenter {

    private ForgetPasscodeContract.View view ;
    public ForgetPasscodePresenter(ForgetPasscodeContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        //Add Service Call logic Here
    }
}
