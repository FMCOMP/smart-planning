package mmjp.fsm.ford.com.mmjp.app.login;

/**
 * Created by mfayajna on 4/3/18.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view ;
    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        //Add Service Call logic Here
    }
}
